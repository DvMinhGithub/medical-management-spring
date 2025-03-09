package com.mdv.hospital.service;

import com.mdv.hospital.dto.request.ChangePasswordDTO;
import com.mdv.hospital.dto.request.CreateUserDTO;
import com.mdv.hospital.dto.request.LoginRequestDTO;
import com.mdv.hospital.dto.request.UpdateUserDTO;
import com.mdv.hospital.dto.response.LoginResponseDTO;
import com.mdv.hospital.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserById(long userId);

    UserResponseDTO getUserByEmail(String email);

    UserResponseDTO register(CreateUserDTO createUserDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    UserResponseDTO getUserInfo(Long id);

    UserResponseDTO updateProfile(UpdateUserDTO userRequestDTO);

    void changePassword(ChangePasswordDTO changePasswordDTO);

    void inactiveUser();
}
