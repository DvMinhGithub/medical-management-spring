package com.mdv.hospital.service;

import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.response.AccountResponseDTO;
import com.mdv.hospital.dto.response.LoginResponseDTO;

public interface AccountService {

    AccountResponseDTO register(CreateAccountRequestDTO requestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    void createAdminUser(CreateAccountRequestDTO requestDTO);
}
