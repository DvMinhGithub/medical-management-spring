package com.mdv.hospital.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ServiceResponseDTO;
import com.mdv.hospital.entity.Facility;
import com.mdv.hospital.entity.Service;
import com.mdv.hospital.exception.ResourceNotFoundException;
import com.mdv.hospital.mapper.ServiceMapper;
import com.mdv.hospital.repository.FacilityRepository;
import com.mdv.hospital.repository.ServiceRepository;
import com.mdv.hospital.service.ServiceService;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final FacilityRepository facilityRepository;
    private final ServiceMapper serviceMapper;

    @Override
    @Transactional
    public ServiceResponseDTO createService(ServiceRequestDTO requestDTO) {
        Service service = serviceMapper.toEntity(requestDTO);
        Facility facility = facilityRepository
                .findById(requestDTO.getFacilityId())
                .orElseThrow(() -> new ResourceNotFoundException("Cơ sở không tồn tại"));
        service.setFacilitysv(facility);
        return serviceMapper.toResponseDTO(serviceRepository.save(service));
    }

    @Override
    public List<ServiceResponseDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toResponseNoFacilityDTO)
                .toList();
    }

    @Override
    public List<ServiceResponseDTO> getAllServicesWithFacility() {
        return serviceRepository.findAllServicesWithFacility().stream()
                .map(serviceMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteService(Long id) {
        Service service = serviceRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dịch vụ không tồn tại"));
        serviceRepository.delete(service);
    }
}
