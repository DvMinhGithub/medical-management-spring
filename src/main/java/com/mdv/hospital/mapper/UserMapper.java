package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.mdv.hospital.dto.request.CreateUserDTO;
import com.mdv.hospital.dto.request.UpdateUserDTO;
import com.mdv.hospital.dto.response.UserResponseDTO;
import com.mdv.hospital.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "service", ignore = true)
    User toEntity(CreateUserDTO createUserDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "service", ignore = true)
    User toEntity(@MappingTarget User user, UpdateUserDTO updateUserDTO);

    UserResponseDTO toDTO(User user);
}
