package com.mdv.hospital.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.ActiveAccountRequestDTO;
import com.mdv.hospital.dto.request.ChangePasswordRequestDTO;
import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.request.ResetPasswordRequestDTO;
import com.mdv.hospital.dto.request.UpdateAccountRequestDTO;
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

    @GetMapping("/check-user/{phone}")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountByPhone(@PathVariable("phone") String phone) {
        AccountResponseDTO accountDto = accountService.getAccountByPhone(phone);
        return ResponseEntity.ok(ApiResponse.success(accountDto, "Lấy thông tin tài khoản thành công!"));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<AccountResponseDTO>> updateProfile(
            @Valid @RequestBody UpdateAccountRequestDTO requestDTO) {
        AccountResponseDTO accountDto = accountService.updateProfile(requestDTO);
        return ResponseEntity.ok(ApiResponse.success(accountDto, "Cập nhật thông tin tài khoản thành công!"));
    }

    @PutMapping("/password/{accountId}")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @PathVariable("accountId") Long accountId, @RequestBody @Valid ChangePasswordRequestDTO requestDTO) {
        accountService.changePassword(accountId, requestDTO);
        return ResponseEntity.ok(ApiResponse.success("Đổi mật khẩu thành công!"));
    }

    @PutMapping("/active/{accountId}")
    public ResponseEntity<ApiResponse<String>> activeAccount(
            @PathVariable("accountId") Long accountId, @Valid @RequestBody ActiveAccountRequestDTO requestDTO) {
        accountService.activeAccount(accountId, requestDTO);
        return ResponseEntity.ok(ApiResponse.success("Kích hoạt tài khoản thành công!"));
    }

    @PutMapping("/reset-password/{accountId}")
    public ResponseEntity<ApiResponse<String>> resetPassword(
            @PathVariable("accountId") Long accountId, @Valid @RequestBody ResetPasswordRequestDTO requestDTO) {
        accountService.resetPassword(accountId, requestDTO);
        return ResponseEntity.ok(ApiResponse.success("Đặt lại mật khẩu thành công!"));
    }
}
