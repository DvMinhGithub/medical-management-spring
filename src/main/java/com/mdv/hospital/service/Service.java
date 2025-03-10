package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ServiceResponseDTO;

public interface Service {
    List<ServiceResponseDTO> getAllServices();

    ServiceResponseDTO getServiceById(Long serviceId);

    ServiceResponseDTO createService(ServiceRequestDTO serviceRequest);

    void deleteService(Long serviceId);
}
