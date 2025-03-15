package com.mdv.hospital.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.mdv.hospital.entity.Order;
import com.mdv.hospital.entity.Service;
import com.mdv.hospital.enums.AccountStatus;
import com.mdv.hospital.enums.AccountType;
import com.mdv.hospital.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private long id;
    private String phone;
    private String fullName;
    private String email;
    private Gender gender;
    private AccountStatus accountStatus;
    private String address;
    private LocalDate dateOfBirth;
    private String code;
    private AccountType type;
    private Service service;
    private Set<Order> orders;
}
