package com.mdv.hospital.service;

import org.springframework.data.domain.Pageable;

import com.mdv.hospital.dto.request.MedicalServiceRequestDTO;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicalServiceResponseDTO;

public interface MedicalServiceService {

    CustomPageResponse<MedicalServiceResponseDTO> getAllServices(Pageable pageable);

    CustomPageResponse<MedicalServiceResponseDTO> getServicesByMedicalFacility(
            Long medicalFacilityId, Pageable pageable);

    MedicalServiceResponseDTO getServiceById(Long serviceId);

    MedicalServiceResponseDTO createService(MedicalServiceRequestDTO request);

    void deleteService(Long serviceId);
}
