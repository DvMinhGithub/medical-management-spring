package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.MedicalServiceRequestDTO;
import com.mdv.hospital.dto.response.MedicalServiceResponseDTO;

public interface MedicalServiceService {

    List<MedicalServiceResponseDTO> getAllServices();

    List<MedicalServiceResponseDTO> getServicesByMedicalFacility(Long medicalFacilityId);

    MedicalServiceResponseDTO getServiceById(Long serviceId);

    MedicalServiceResponseDTO createService(MedicalServiceRequestDTO request);

    void deleteService(Long serviceId);
}
