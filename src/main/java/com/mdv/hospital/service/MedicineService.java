package com.mdv.hospital.service;

import org.springframework.data.domain.Pageable;

import com.mdv.hospital.dto.request.MedicineRequest;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicineResponseDTO;

public interface MedicineService {
    CustomPageResponse<MedicineResponseDTO> getAllMedicines(Pageable pageable);

    MedicineResponseDTO getMedicineById(Long medicineId);

    MedicineResponseDTO createMedicine(MedicineRequest medicineRequest);

    void deleteMedicine(Long medicineId);
}
