package com.mdv.hospital.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.ServiceRequestDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.ServiceResponseDTO;
import com.mdv.hospital.service.ServiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> createService(
            @Valid @RequestBody ServiceRequestDTO requestDTO) {
        return ResponseEntity.ok(
                ApiResponse.success(serviceService.createService(requestDTO), "Tạo dịch vụ thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceResponseDTO>>> getAllServices() {
        return ResponseEntity.ok(
                ApiResponse.success(serviceService.getAllServices(), "Lấy danh sách dịch vụ thành công"));
    }

    @GetMapping("/with-facility")
    public ResponseEntity<ApiResponse<List<ServiceResponseDTO>>> getAllServicesWithFacility() {
        return ResponseEntity.ok(ApiResponse.success(
                serviceService.getAllServicesWithFacility(), "Lấy danh sách dịch vụ với cơ sở thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteService(@PathVariable("id") Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa dịch vụ thành công"));
    }
}
