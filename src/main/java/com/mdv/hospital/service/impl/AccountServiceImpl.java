package com.mdv.hospital.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mdv.hospital.dto.request.ActiveAccountRequestDTO;
import com.mdv.hospital.dto.request.ChangePasswordRequestDTO;
import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.request.ResetPasswordRequestDTO;
import com.mdv.hospital.dto.request.UpdateAccountRequestDTO;
import com.mdv.hospital.dto.response.AccountResponseDTO;
import com.mdv.hospital.dto.response.LoginResponseDTO;
import com.mdv.hospital.entity.Account;
import com.mdv.hospital.enums.AccountStatus;
import com.mdv.hospital.enums.AccountType;
import com.mdv.hospital.exception.BadRequestException;
import com.mdv.hospital.exception.ConflictException;
import com.mdv.hospital.exception.ResourceNotFoundException;
import com.mdv.hospital.mapper.AccountMapper;
import com.mdv.hospital.repository.AccountRepository;
import com.mdv.hospital.security.JwtTokenProvider;
import com.mdv.hospital.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AccountResponseDTO register(CreateAccountRequestDTO requestDTO) {
        if (accountRepository.existsByEmail(requestDTO.getEmail())) {
            throw new ConflictException("Email đã được sử dụng!");
        }

        if (accountRepository.existsByPhone(requestDTO.getPhone())) {
            throw new ConflictException("Số điện thoại đã được sử dụng!");
        }

        Account account = accountMapper.toEntity(requestDTO);

        account.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        account.setType(AccountType.PATIENT);
        account.setAccountStatus(AccountStatus.INACTIVE);
        Account savedAccount = accountRepository.save(account);
        account.setCode(generateAccountCode(account.getType(), savedAccount.getId()));
        savedAccount = accountRepository.save(account);

        return accountMapper.toDTO(savedAccount);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Account account = accountRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email không chính xác!"));

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new BadRequestException("Mật khẩu không chính xác!");
        }

        if (account.getAccountStatus() == AccountStatus.INACTIVE) {
            throw new BadRequestException("Tài khoản chưa được kích hoạt!");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String generateAccessToken = jwtTokenProvider.generateAccessToken(userDetails);
        String generateRefreshToken = jwtTokenProvider.generateRefreshToken(userDetails);
        Date expiration = jwtTokenProvider.getExpirationDateFromToken(generateAccessToken);

        return LoginResponseDTO.builder()
                .accessToken(generateAccessToken)
                .refreshToken(generateRefreshToken)
                .expiredAt(expiration)
                .build();
    }

    @Override
    public List<AccountResponseDTO> getAccount() {
        return accountRepository.findAll().stream().map(accountMapper::toDTO).toList();
    }

    @Override
    public List<AccountResponseDTO> getAccountPatient() {
        return accountRepository.findAllByType(AccountType.PATIENT).stream()
                .map(accountMapper::toDTO)
                .toList();
    }

    @Override
    public List<AccountResponseDTO> getAccountPatientWithDoneOrders() {
        return accountRepository.findPatientsWithDoneOrders().stream()
                .map(accountMapper::toDTOEager)
                .toList();
    }

    @Override
    public List<AccountResponseDTO> getAccountDoctor() {
        return accountRepository.findAllByType(AccountType.DOCTOR).stream()
                .map(accountMapper::toDTOEager)
                .toList();
    }

    private String generateAccountCode(AccountType type, long userId) {
        String prefix = "";
        switch (type) {
            case PATIENT:
                prefix = "PAT";
                break;
            case DOCTOR:
                prefix = "DOC";
                break;
            case ADMIN:
                prefix = "ADM";
                break;
        }
        return prefix + "-" + String.format("%06d", userId);
    }

    @Override
    public void createAdminUser(CreateAccountRequestDTO requestDTO) {
        if (accountRepository.existsByEmail(requestDTO.getEmail())) {
            log.info("Admin user already exists");
            return;
        }
        Account user = accountMapper.toEntity(requestDTO);

        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setType(AccountType.ADMIN);
        user.setAccountStatus(AccountStatus.ACTIVE);
        Account savedUser = accountRepository.save(user);

        user.setCode(generateAccountCode(user.getType(), savedUser.getId()));
        accountRepository.save(user);
        log.info("Admin user created successfully");
    }

    @Override
    public AccountResponseDTO updateProfile(UpdateAccountRequestDTO requestDTO) {
        Account account = accountRepository
                .findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

        Account updatedAccount = accountMapper.toEntity(account, requestDTO);
        Account savedAccount = accountRepository.save(updatedAccount);

        return accountMapper.toDTO(savedAccount);
    }

    @Override
    public void changePassword(Long accountId, ChangePasswordRequestDTO requestDTO) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

        if (!passwordEncoder.matches(requestDTO.getOldPassword(), account.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác!");
        }

        account.setPassword(passwordEncoder.encode(requestDTO.getNewPassword()));
        accountRepository.save(account);
    }

    @Override
    public void activeAccount(Long accountId, ActiveAccountRequestDTO requestDTO) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

        if (!account.getCode().equals(requestDTO.getCode())) {
            throw new BadRequestException("Mã kích hoạt không chính xác!");
        }

        account.setAccountStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }

    @Override
    public void resetPassword(Long accountId, ResetPasswordRequestDTO requestDTO) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

        account.setPassword(passwordEncoder.encode(requestDTO.getNewPassword()));
        accountRepository.save(account);
    }

    @Override
    public AccountResponseDTO getAccountByPhone(String phone) {
        Account account = accountRepository
                .findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Tài khoản không tồn tại!"));

        return accountMapper.toDTO(account);
    }
}
