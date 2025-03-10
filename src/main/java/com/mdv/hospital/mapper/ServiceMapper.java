package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ServiceResponseDTO;
import com.mdv.hospital.entity.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(target = "facilityId", source = "medicalFacility.id")
    ServiceResponseDTO toServiceResponseDTO(Service service);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalFacility", ignore = true)
    Service toService(ServiceRequestDTO serviceRequestDTO);
}
