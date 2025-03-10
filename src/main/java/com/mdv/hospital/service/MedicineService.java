package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.MedicineRequest;
import com.mdv.hospital.dto.response.MedicineResponseDTO;

public interface MedicineService {
    List<MedicineResponseDTO> getAllMedicines();

    MedicineResponseDTO getMedicineById(Long medicineId);

    MedicineResponseDTO createMedicine(MedicineRequest medicineRequest);

    void deleteMedicine(Long medicineId);
}
