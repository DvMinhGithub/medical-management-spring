package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.mdv.hospital.dto.request.CreateAccountRequestDTO;
import com.mdv.hospital.dto.request.UpdateAccountRequestDTO;
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

    @Mapping(target = "service", ignore = true)
    @Mapping(target = "orders", ignore = true)
    AccountResponseDTO toDTO(Account account);

    @Mapping(target = "service", source = "serviceac")
    @Mapping(target = "orders", source = "history")
    AccountResponseDTO toDTOEager(Account account);

    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "doctorSchedule", ignore = true)
    @Mapping(target = "facility", ignore = true)
    @Mapping(target = "history", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serviceac", ignore = true)
    @Mapping(target = "type", ignore = true)
    Account toEntity(@MappingTarget Account account, UpdateAccountRequestDTO requestDTO);
}
