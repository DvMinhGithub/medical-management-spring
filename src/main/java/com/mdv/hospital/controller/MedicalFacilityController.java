package com.mdv.hospital.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.MedicalFacilityRequestDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicalFacilityResponseDTO;
import com.mdv.hospital.service.MedicalFacilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medical-facilities")
@RequiredArgsConstructor
public class MedicalFacilityController {
    private final MedicalFacilityService medicalFacilityService;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicalFacilityResponseDTO>> createMedicalFacility(
            @Valid @RequestBody MedicalFacilityRequestDTO medicalFacilityRequestDTO) {
        MedicalFacilityResponseDTO medicalFacility =
                medicalFacilityService.createMedicalFacility(medicalFacilityRequestDTO);

        return ResponseEntity.ok(ApiResponse.success(medicalFacility, "Tạo cơ sở y tế thành công!"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomPageResponse<MedicalFacilityResponseDTO>>> getAllMedicalFacilities(
            Pageable pageable) {
        CustomPageResponse<MedicalFacilityResponseDTO> medicalFacility =
                medicalFacilityService.getAllMedicalFacilities(pageable);
        return ResponseEntity.ok(ApiResponse.success(medicalFacility, "Lấy danh sách cơ sở y tế thành công!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicalFacilityResponseDTO>> getMedicalFacility(@PathVariable("id") Long id) {
        MedicalFacilityResponseDTO medicalFacility = medicalFacilityService.getMedicalFacility(id);
        return ResponseEntity.ok(ApiResponse.success(medicalFacility, "Lấy thông tin cơ sở y tế thành công!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicalFacilityResponseDTO>> updateMedicalFacility(
            @PathVariable("id") Long id, @Valid @RequestBody MedicalFacilityRequestDTO medicalFacilityRequestDTO) {
        MedicalFacilityResponseDTO medicalFacility =
                medicalFacilityService.updateMedicalFacility(id, medicalFacilityRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(medicalFacility, "Cập nhật cơ sở y tế thành công!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMedicalFacility(@PathVariable("id") Long id) {
        medicalFacilityService.deleteMedicalFacility(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa cơ sở y tế thành công!"));
    }
}
