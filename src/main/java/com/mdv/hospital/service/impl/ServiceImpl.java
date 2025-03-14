package com.mdv.hospital.service.impl;

import java.util.List;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ServiceResponseDTO;
import com.mdv.hospital.entity.MedicalFacility;
import com.mdv.hospital.mapper.ServiceMapper;
import com.mdv.hospital.repository.MedicalFacilityRepository;
import com.mdv.hospital.repository.ServiceRepository;
import com.mdv.hospital.service.Service;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements Service {

    private final ServiceRepository serviceRepository;
    private final MedicalFacilityRepository medicalFacilityRepository;

    private final ServiceMapper serviceMapper;

    private static final String SERVICE_NOT_FOUND = "Service not found";

    @Override
    public List<ServiceResponseDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toServiceResponseDTO)
                .toList();
    }

    @Override
    public ServiceResponseDTO getServiceById(Long serviceId) {
        return serviceRepository
                .findById(serviceId)
                .map(serviceMapper::toServiceResponseDTO)
                .orElseThrow(() -> new RuntimeException(SERVICE_NOT_FOUND));
    }

    @Override
    public List<ServiceResponseDTO> getServicesByMedicalFacility(Long medicalFacilityId) {
        return serviceRepository.findAllByMedicalFacilityId(medicalFacilityId).stream()
                .map(serviceMapper::toServiceResponseDTO)
                .toList();
    }

    @Override
    public ServiceResponseDTO createService(ServiceRequestDTO serviceRequest) {
        MedicalFacility facility = medicalFacilityRepository
                .findById(serviceRequest.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        com.mdv.hospital.entity.Service service = serviceMapper.toService(serviceRequest);
        service.setMedicalFacility(facility);

        return serviceMapper.toServiceResponseDTO(serviceRepository.save(service));
    }

    @Override
    public void deleteService(Long serviceId) {
        com.mdv.hospital.entity.Service service =
                serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException(SERVICE_NOT_FOUND));
        serviceRepository.delete(service);
    }
}
