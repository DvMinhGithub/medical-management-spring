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
import com.mdv.hospital.service.Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {
    private final Service service;

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> createService(
            @Valid @RequestBody ServiceRequestDTO serviceRequestDTO) {
        ServiceResponseDTO serviceResponseDTO = service.createService(serviceRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(serviceResponseDTO, "Tạo dịch vụ thành công!"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceResponseDTO>>> getAllServices() {
        List<ServiceResponseDTO> services = service.getAllServices();
        return ResponseEntity.ok(ApiResponse.success(services, "Lấy tất cả dịch vụ thành công!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> getServiceById(@PathVariable("id") Long id) {
        ServiceResponseDTO serviceResponseDTO = service.getServiceById(id);
        return ResponseEntity.ok(ApiResponse.success(serviceResponseDTO, "Lấy dịch vụ thành công!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteService(@PathVariable("id") Long id) {
        service.deleteService(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa dịch vụ thành công!"));
    }
}
