package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ServiceResponseDTO;
import com.mdv.hospital.entity.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "facilitysv", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "results", ignore = true)
    Service toEntity(ServiceRequestDTO dto);

    @Mapping(target = "facility", source = "facilitysv")
    ServiceResponseDTO toResponseDTO(Service entity);

    @Mapping(target = "facility", ignore = true)
    ServiceResponseDTO toResponseNoFacilityDTO(Service entity);
}
