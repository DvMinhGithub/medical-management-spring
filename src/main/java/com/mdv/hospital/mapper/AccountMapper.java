package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.response.AccountResponseDTO;
import com.mdv.hospital.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "doctorSchedule", ignore = true)
    @Mapping(target = "facility", ignore = true)
    @Mapping(target = "history", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serviceac", ignore = true)
    @Mapping(target = "type", ignore = true)
    Account toEntity(CreateAccountRequestDTO requestDTO);

    @Mapping(target = "serviceId", ignore = true)
    AccountResponseDTO toDTO(Account user);
}
