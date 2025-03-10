package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.MedicalFacilityRequestDTO;
import com.mdv.hospital.dto.response.MedicalFacilityResponseDTO;

public interface MedicalFacilityService {
    MedicalFacilityResponseDTO createMedicalFacility(MedicalFacilityRequestDTO medicalFacilityRequestDTO);

    MedicalFacilityResponseDTO updateMedicalFacility(Long id, MedicalFacilityRequestDTO medicalFacilityRequestDTO);

    void deleteMedicalFacility(Long id);

    MedicalFacilityResponseDTO getMedicalFacility(Long id);

    List<MedicalFacilityResponseDTO> getAllMedicalFacilities();
}
