package com.mdv.hospital.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.response.AccountResponseDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.LoginResponseDTO;
import com.mdv.hospital.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> register(
            @Valid @RequestBody CreateAccountRequestDTO requestDTO) {
        AccountResponseDTO accountDto = accountService.register(requestDTO);
        return ResponseEntity.ok(ApiResponse.success(accountDto, "Đăng ký thành công tài khoản mới!"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO loginResponseDTO = accountService.login(request);
        return ResponseEntity.ok(ApiResponse.success(loginResponseDTO, "Đăng nhập thành công!"));
    }
}
