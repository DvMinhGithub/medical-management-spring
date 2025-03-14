package com.mdv.hospital.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mdv.hospital.dto.request.MedicalServiceRequestDTO;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicalServiceResponseDTO;
import com.mdv.hospital.entity.MedicalFacility;
import com.mdv.hospital.entity.MedicalService;
import com.mdv.hospital.mapper.MedicalServiceMapper;
import com.mdv.hospital.repository.MedicalFacilityRepository;
import com.mdv.hospital.repository.MedicalServiceRepository;
import com.mdv.hospital.service.MedicalServiceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicalServiceImpl implements MedicalServiceService {

    private final MedicalServiceRepository serviceRepository;
    private final MedicalFacilityRepository medicalFacilityRepository;

    private final MedicalServiceMapper medicalServiceMapper;

    private static final String SERVICE_NOT_FOUND = "MedicalService not found";

    @Override
    public CustomPageResponse<MedicalServiceResponseDTO> getAllServices(Pageable pageable) {
        Page<MedicalServiceResponseDTO> services =
                serviceRepository.findAll(pageable).map(medicalServiceMapper::toServiceResponseDTO);
        return new CustomPageResponse<>(services);
    }

    @Override
    public MedicalServiceResponseDTO getServiceById(Long serviceId) {
        return serviceRepository
                .findById(serviceId)
                .map(medicalServiceMapper::toServiceResponseDTO)
                .orElseThrow(() -> new RuntimeException(SERVICE_NOT_FOUND));
    }

    @Override
    public CustomPageResponse<MedicalServiceResponseDTO> getServicesByMedicalFacility(
            Long medicalFacilityId, Pageable pageable) {
        Page<MedicalServiceResponseDTO> services = serviceRepository
                .findAllByMedicalFacilityId(medicalFacilityId, pageable)
                .map(medicalServiceMapper::toServiceResponseDTO);
        return new CustomPageResponse<>(services);
    }

    @Override
    public MedicalServiceResponseDTO createService(MedicalServiceRequestDTO serviceRequest) {
        MedicalFacility facility = medicalFacilityRepository
                .findById(serviceRequest.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        MedicalService service = medicalServiceMapper.toService(serviceRequest);
        service.setMedicalFacility(facility);

        return medicalServiceMapper.toServiceResponseDTO(serviceRepository.save(service));
    }

    @Override
    public void deleteService(Long serviceId) {
        com.mdv.hospital.entity.MedicalService service =
                serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException(SERVICE_NOT_FOUND));
        serviceRepository.delete(service);
    }
}
