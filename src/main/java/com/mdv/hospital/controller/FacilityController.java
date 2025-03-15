package com.mdv.hospital.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.FacilityRequestDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.FacilityResponseDTO;
import com.mdv.hospital.service.FacilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<ApiResponse<FacilityResponseDTO>> createFacility(
            @Valid @RequestBody FacilityRequestDTO requestDTO) {
        FacilityResponseDTO responseDTO = facilityService.createFacility(requestDTO);
        return ResponseEntity.ok(ApiResponse.success(responseDTO, "Cơ sở đã được tạo thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FacilityResponseDTO>>> getAllFacilities() {
        return ResponseEntity.ok(ApiResponse.success(facilityService.getAllFacilities(), "Danh sách cơ sở y tế"));
    }
}
