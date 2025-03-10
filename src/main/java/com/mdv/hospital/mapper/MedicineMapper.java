package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.mdv.hospital.dto.request.MedicineRequest;
import com.mdv.hospital.dto.response.MedicineResponseDTO;
import com.mdv.hospital.entity.Medicine;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    @Mapping(target = "id", ignore = true)
    Medicine toEntity(MedicineRequest medicineRequest);

    MedicineResponseDTO toResponse(Medicine medicine);

    @Mapping(target = "id", ignore = true)
    MedicineResponseDTO updateEntity(@MappingTarget MedicineResponseDTO medicine, MedicineRequest medicineRequest);
}
