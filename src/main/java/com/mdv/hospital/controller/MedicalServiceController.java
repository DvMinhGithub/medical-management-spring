package com.mdv.hospital.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.MedicalServiceRequestDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicalServiceResponseDTO;
import com.mdv.hospital.service.MedicalServiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class MedicalServiceController {
    private final MedicalServiceService service;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicalServiceResponseDTO>> createMedicalService(
            @Valid @RequestBody MedicalServiceRequestDTO serviceRequestDTO) {
        MedicalServiceResponseDTO serviceResponseDTO = service.createService(serviceRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(serviceResponseDTO, "Tạo dịch vụ thành công!"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomPageResponse<MedicalServiceResponseDTO>>> getAllServices(
            Pageable pageable) {
        CustomPageResponse<MedicalServiceResponseDTO> services = service.getAllServices(pageable);
        return ResponseEntity.ok(ApiResponse.success(services, "Lấy tất cả dịch vụ thành công!"));
    }

    @GetMapping("/facility/{id}")
    public ResponseEntity<ApiResponse<CustomPageResponse<MedicalServiceResponseDTO>>> getServicesByMedicalFacility(
            @PathVariable("id") Long medicalFacilityId, Pageable pageable) {
        CustomPageResponse<MedicalServiceResponseDTO> services =
                service.getServicesByMedicalFacility(medicalFacilityId, pageable);
        return ResponseEntity.ok(ApiResponse.success(services, "Lấy dịch vụ theo cơ sở y tế thành công!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicalServiceResponseDTO>> getMedicalServiceById(@PathVariable("id") Long id) {
        MedicalServiceResponseDTO serviceResponseDTO = service.getServiceById(id);
        return ResponseEntity.ok(ApiResponse.success(serviceResponseDTO, "Lấy dịch vụ thành công!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMedicalService(@PathVariable("id") Long id) {
        service.deleteService(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa dịch vụ thành công!"));
    }
}
