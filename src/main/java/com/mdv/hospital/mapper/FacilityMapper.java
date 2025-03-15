package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mdv.hospital.dto.request.FacilityRequestDTO;
import com.mdv.hospital.dto.response.FacilityResponseDTO;
import com.mdv.hospital.entity.Facility;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "facility_name", source = "facilityName")
    Facility toEntity(FacilityRequestDTO requestDTO);

    @Mapping(target = "facilityName", source = "facility_name")
    FacilityResponseDTO toDTO(Facility facility);
}
