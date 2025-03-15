package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ServiceResponseDTO;

public interface ServiceService {

    ServiceResponseDTO createService(ServiceRequestDTO requestDTO);

    List<ServiceResponseDTO> getAllServices();

    List<ServiceResponseDTO> getAllServicesWithFacility();

    void deleteService(Long id);
}
