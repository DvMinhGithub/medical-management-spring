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
import org.springframework.transaction.annotation.Transactional;

import com.mdv.hospital.dto.request.ChangePasswordDTO;
import com.mdv.hospital.dto.request.CreateUserDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.request.UpdateUserDTO;
import com.mdv.hospital.dto.response.LoginResponseDTO;
import com.mdv.hospital.dto.response.MedicalServiceResponseDTO;
import com.mdv.hospital.dto.response.UserResponseDTO;
import com.mdv.hospital.entity.User;
import com.mdv.hospital.enums.AppointmentStatus;
import com.mdv.hospital.enums.UserRole;
import com.mdv.hospital.enums.UserStatus;
import com.mdv.hospital.exception.BadRequestException;
import com.mdv.hospital.exception.ConflictException;
import com.mdv.hospital.exception.ResourceNotFoundException;
import com.mdv.hospital.mapper.UserMapper;
import com.mdv.hospital.repository.UserRepository;
import com.mdv.hospital.security.CustomUserDetailsService;
import com.mdv.hospital.security.JwtTokenProvider;
import com.mdv.hospital.service.MedicalServiceService;
import com.mdv.hospital.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MedicalServiceService service;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    private static String userNotFoundMessage = "Không tìm thấy người dùng";

    @Override
    public UserResponseDTO getUserById(long userId) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userNotFoundMessage));

        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user =
                userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(userNotFoundMessage));

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getUserByRole(String role) {
        UserRole userRole;
        try {
            userRole = UserRole.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Role không hợp lệ!");
        }
        List<UserResponseDTO> user = userRepository.findAllByRole(userRole).stream()
                .map(userMapper::toDTO)
                .toList();
        return user;
    }

    @Override
    public List<UserResponseDTO> getUsersByAppoinmenStatus(String status) {
        AppointmentStatus appointmentStatus;
        try {
            appointmentStatus = AppointmentStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Trạng thái không hợp lệ!");
        }
        List<UserResponseDTO> user = userRepository.findAllByAppoinmenStatus(appointmentStatus).stream()
                .map(userMapper::toDTO)
                .toList();
        return user;
    }

    @Override
    public List<UserResponseDTO> getUsersByServiceId(Long serviceId) {
        MedicalServiceResponseDTO serviceDto = service.getServiceById(serviceId);
        List<UserResponseDTO> user = userRepository.findAllByServiceId(serviceDto.getId()).stream()
                .map(userMapper::toDTO)
                .toList();
        return user;
    }

    @Override
    @Transactional
    public UserResponseDTO register(CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new ConflictException("Email đã được sử dụng!");
        }

        if (userRepository.existsByPhone(createUserDTO.getPhone())) {
            throw new ConflictException("Số điện thoại đã được sử dụng!");
        }

        User user = userMapper.toEntity(createUserDTO);

        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole(UserRole.PATIENT);
        user.setUserStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        user.setCode(generateUserCode(user.getRole(), savedUser.getId()));
        savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public void createAdminUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            return;
        }
        User user = userMapper.toEntity(createUserDTO);

        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole(UserRole.ADMIN);
        user.setUserStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        user.setCode(generateUserCode(user.getRole(), savedUser.getId()));
        userRepository.save(user);
    }

    private String generateUserCode(UserRole role, long userId) {
        String prefix = "";
        switch (role) {
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
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email không chính xác!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Mật khẩu không chính xác!");
        }

        if (user.getUserStatus() == UserStatus.INACTIVE) {
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
    public UserResponseDTO getUserInfo(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(userNotFoundMessage));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO updateProfile(UpdateUserDTO userRequestDTO) {
        Long userId = customUserDetailsService.getCurrentUserId();
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userNotFoundMessage));

        User updatedUser = userMapper.toEntity(user, userRequestDTO);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        Long userId = customUserDetailsService.getCurrentUserId();
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userNotFoundMessage));

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác!");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void inactiveUser() {
        Long userId = customUserDetailsService.getCurrentUserId();
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userNotFoundMessage));

        user.setUserStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }
}
