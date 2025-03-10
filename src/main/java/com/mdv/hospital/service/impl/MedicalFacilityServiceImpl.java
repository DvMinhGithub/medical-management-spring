package com.mdv.hospital.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mdv.hospital.dto.request.MedicalFacilityRequestDTO;
import com.mdv.hospital.dto.response.MedicalFacilityResponseDTO;
import com.mdv.hospital.entity.MedicalFacility;
import com.mdv.hospital.mapper.MedicalFacilityMapper;
import com.mdv.hospital.repository.MedicalFacilityRepository;
import com.mdv.hospital.service.MedicalFacilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicalFacilityServiceImpl implements MedicalFacilityService {

    private final MedicalFacilityRepository medicalFacilityRepository;

    private final MedicalFacilityMapper medicalFacilityMapper;

    private static final String NOT_FOUND_MESSAGE = "Không tìm thấy cơ sở y tế ";

    @Override
    public MedicalFacilityResponseDTO createMedicalFacility(MedicalFacilityRequestDTO medicalFacilityRequestDTO) {

        MedicalFacility medicalFacility = medicalFacilityMapper.toEntity(medicalFacilityRequestDTO);

        return medicalFacilityMapper.toResponse(medicalFacilityRepository.save(medicalFacility));
    }

    @Override
    public MedicalFacilityResponseDTO updateMedicalFacility(
            Long id, MedicalFacilityRequestDTO medicalFacilityRequestDTO) {
        MedicalFacility medicalFacility = medicalFacilityRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));
        medicalFacilityMapper.updateEntity(medicalFacility, medicalFacilityRequestDTO);
        return medicalFacilityMapper.toResponse(medicalFacilityRepository.save(medicalFacility));
    }

    @Override
    public void deleteMedicalFacility(Long id) {
        MedicalFacility medicalFacility = medicalFacilityRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));

        medicalFacilityRepository.delete(medicalFacility);
    }

    @Override
    public MedicalFacilityResponseDTO getMedicalFacility(Long id) {
        MedicalFacility medicalFacility = medicalFacilityRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));

        return medicalFacilityMapper.toResponse(medicalFacility);
    }

    @Override
    public List<MedicalFacilityResponseDTO> getAllMedicalFacilities() {
        return medicalFacilityRepository.findAll().stream()
                .map(medicalFacilityMapper::toResponse)
                .toList();
    }
}
