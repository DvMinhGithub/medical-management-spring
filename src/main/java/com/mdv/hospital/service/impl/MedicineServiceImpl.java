package com.mdv.hospital.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mdv.hospital.dto.request.MedicineRequestDTO;
import com.mdv.hospital.dto.response.MedicineResponseDTO;
import com.mdv.hospital.entity.Medicine;
import com.mdv.hospital.mapper.MedicineMapper;
import com.mdv.hospital.repository.MedicineRespository;
import com.mdv.hospital.service.MedicineService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRespository medicineRespository;
    private final MedicineMapper medicineMapper;

    @Override
    public MedicineResponseDTO createMedicine(MedicineRequestDTO requestDTO) {
        Medicine medicine = medicineMapper.toEntity(requestDTO);
        return medicineMapper.toResponseDTO(medicineRespository.save(medicine));
    }

    @Override
    public List<MedicineResponseDTO> getAllMedicines() {
        return medicineRespository.findAll().stream()
                .map(medicineMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteMedicine(Long id) {
        Medicine medicine = medicineRespository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thuốc với id: " + id));
        medicineRespository.delete(medicine);
    }
}
