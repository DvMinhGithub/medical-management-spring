package com.mdv.hospital.dto.response;

import java.time.LocalDate;

import com.mdv.hospital.enums.UserRole;
import com.mdv.hospital.enums.UserStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long id;
    private UserStatus userStatus;
    private String address;
    private String code;
    private LocalDate dateOfBirth;
    private String email;
    private String fullName;
    private String gender;
    private String phone;
    private UserRole role;
}
