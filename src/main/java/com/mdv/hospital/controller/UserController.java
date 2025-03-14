package com.mdv.hospital.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.ChangePasswordDTO;
import com.mdv.hospital.dto.request.CreateUserDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.request.UpdateUserDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.LoginResponseDTO;
import com.mdv.hospital.dto.response.UserResponseDTO;
import com.mdv.hospital.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserInfo(@PathVariable("id") Long id) {
        UserResponseDTO userResponseDTO = userService.getUserInfo(id);
        return ResponseEntity.ok(ApiResponse.success(userResponseDTO, "Lấy thông tin tài khoản thành công!"));
    }

    @GetMapping("/info/email/{email}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserInfoByEmail(@PathVariable("email") String email) {
        UserResponseDTO userResponseDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(ApiResponse.success(userResponseDTO, "Lấy thông tin tài khoản thành công!"));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<ApiResponse<CustomPageResponse<UserResponseDTO>>> getUserInfoByRole(
            @PathVariable("role") String role, Pageable pageable) {
        CustomPageResponse<UserResponseDTO> userResponseDTO = userService.getUserByRole(role, pageable);
        return ResponseEntity.ok(ApiResponse.success(userResponseDTO, "Lấy thông tin tài khoản thành công!"));
    }

    @GetMapping("/appointment-status/{status}")
    public ResponseEntity<ApiResponse<CustomPageResponse<UserResponseDTO>>> getUsersByAppoinmenStatus(
            @PathVariable("status") String status, Pageable pageable) {
        CustomPageResponse<UserResponseDTO> userResponseDTO = userService.getUsersByAppoinmenStatus(status, pageable);
        return ResponseEntity.ok(ApiResponse.success(userResponseDTO, "Lấy thông tin tài khoản thành công!"));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ApiResponse<CustomPageResponse<UserResponseDTO>>> getUsersByServiceId(
            @PathVariable("serviceId") Long serviceId, Pageable pageable) {
        CustomPageResponse<UserResponseDTO> userResponseDTO = userService.getUsersByServiceId(serviceId, pageable);
        return ResponseEntity.ok(ApiResponse.success(userResponseDTO, "Lấy thông tin tài khoản thành công!"));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userService.register(createUserDTO);
        return ResponseEntity.ok(ApiResponse.success("Đăng ký thành công tài khoản mới!"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = userService.login(loginRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(loginResponseDTO, "Đăng nhập thành công!"));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateProfile(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        UserResponseDTO userResponseDTO = userService.updateProfile(updateUserDTO);
        return ResponseEntity.ok(ApiResponse.success(userResponseDTO, "Cập nhật tài khoản thành công!"));
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        userService.changePassword(changePasswordDTO);
        return ResponseEntity.ok(ApiResponse.success("Đổi mật khẩu thành công!"));
    }

    @PutMapping("/inactive")
    public ResponseEntity<ApiResponse<String>> inactiveUser() {
        userService.inactiveUser();
        return ResponseEntity.ok(ApiResponse.success("Tài khoản đã bị vô hiệu hóa!"));
    }
}
