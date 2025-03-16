package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.MedicineRequestDTO;
import com.mdv.hospital.dto.response.MedicineResponseDTO;

public interface MedicineService {
    MedicineResponseDTO createMedicine(MedicineRequestDTO requestDTO);

    List<MedicineResponseDTO> getAllMedicines();

    void deleteMedicine(Long id);
}
