package com.mdv.hospital.service;

import org.springframework.data.domain.Pageable;

import com.mdv.hospital.dto.request.MedicalFacilityRequestDTO;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicalFacilityResponseDTO;

public interface MedicalFacilityService {
    MedicalFacilityResponseDTO createMedicalFacility(MedicalFacilityRequestDTO medicalFacilityRequestDTO);

    MedicalFacilityResponseDTO updateMedicalFacility(Long id, MedicalFacilityRequestDTO medicalFacilityRequestDTO);

    void deleteMedicalFacility(Long id);

    MedicalFacilityResponseDTO getMedicalFacility(Long id);

    CustomPageResponse<MedicalFacilityResponseDTO> getAllMedicalFacilities(Pageable pageable);
}
