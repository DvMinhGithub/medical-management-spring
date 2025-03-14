package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mdv.hospital.dto.request.MedicalServiceRequestDTO;
import com.mdv.hospital.dto.response.MedicalServiceResponseDTO;
import com.mdv.hospital.entity.MedicalService;

@Mapper(componentModel = "spring")
public interface MedicalServiceMapper {
    @Mapping(target = "facilityId", source = "medicalFacility.id")
    MedicalServiceResponseDTO toServiceResponseDTO(MedicalService service);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalFacility", ignore = true)
    @Mapping(target = "users", ignore = true)
    MedicalService toService(MedicalServiceRequestDTO requestDTO);
}
