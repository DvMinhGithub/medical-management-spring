package com.mdv.hospital.service;

import java.util.List;

import com.mdv.hospital.dto.request.FacilityRequestDTO;
import com.mdv.hospital.dto.response.FacilityResponseDTO;

public interface FacilityService {

    FacilityResponseDTO createFacility(FacilityRequestDTO requestDTO);

    List<FacilityResponseDTO> getAllFacilities();
}
