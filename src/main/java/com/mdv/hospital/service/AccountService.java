package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.ActiveAccountRequestDTO;
import com.mdv.hospital.dto.request.ChangePasswordRequestDTO;
import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.request.ResetPasswordRequestDTO;
import com.mdv.hospital.dto.request.UpdateAccountRequestDTO;
import com.mdv.hospital.dto.response.AccountResponseDTO;
import com.mdv.hospital.dto.response.LoginResponseDTO;

public interface AccountService {

    AccountResponseDTO register(CreateAccountRequestDTO requestDTO);

    void createAdminUser(CreateAccountRequestDTO requestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    AccountResponseDTO getAccountByPhone(String phone);

    List<AccountResponseDTO> getAccount();

    List<AccountResponseDTO> getAccountPatient();

    List<AccountResponseDTO> getAccountPatientWithDoneOrders();

    List<AccountResponseDTO> getAccountDoctor();

    AccountResponseDTO updateProfile(UpdateAccountRequestDTO requestDTO);

    void changePassword(Long accountId, ChangePasswordRequestDTO requestDTO);

    void activeAccount(Long accountId, ActiveAccountRequestDTO requestDTO);

    void resetPassword(Long accountId, ResetPasswordRequestDTO requestDTO);
}
