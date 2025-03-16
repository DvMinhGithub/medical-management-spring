package com.mdv.hospital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mdv.hospital.dto.request.MedicineRequestDTO;
import com.mdv.hospital.dto.response.MedicineResponseDTO;
import com.mdv.hospital.entity.Medicine;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescriptionItems", ignore = true)
    Medicine toEntity(MedicineRequestDTO requestDTO);

    MedicineResponseDTO toResponseDTO(Medicine entity);
}
