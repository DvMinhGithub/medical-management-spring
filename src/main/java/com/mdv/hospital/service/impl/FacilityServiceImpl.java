package com.mdv.hospital.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mdv.hospital.dto.request.FacilityRequestDTO;
import com.mdv.hospital.dto.response.FacilityResponseDTO;
import com.mdv.hospital.entity.Facility;
import com.mdv.hospital.mapper.FacilityMapper;
import com.mdv.hospital.repository.FacilityRepository;
import com.mdv.hospital.service.FacilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    @Override
    public FacilityResponseDTO createFacility(FacilityRequestDTO requestDTO) {
        Facility facility = facilityMapper.toEntity(requestDTO);
        return facilityMapper.toDTO(facilityRepository.save(facility));
    }

    @Override
    public List<FacilityResponseDTO> getAllFacilities() {
        return facilityRepository.findAll().stream().map(facilityMapper::toDTO).toList();
    }
}
