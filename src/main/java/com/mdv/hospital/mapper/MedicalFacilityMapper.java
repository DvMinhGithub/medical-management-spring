package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.mdv.hospital.dto.request.MedicalFacilityRequestDTO;
import com.mdv.hospital.dto.response.MedicalFacilityResponseDTO;
import com.mdv.hospital.entity.MedicalFacility;

@Mapper(componentModel = "spring")
public interface MedicalFacilityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "admin", ignore = true)
    MedicalFacility toEntity(MedicalFacilityRequestDTO medicalFacilityRequestDTO);

    MedicalFacilityResponseDTO toResponse(MedicalFacility medicalFacility);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "admin", ignore = true)
    MedicalFacility updateEntity(
            @MappingTarget MedicalFacility medicalFacility, MedicalFacilityRequestDTO medicalFacilityRequestDTO);
}
