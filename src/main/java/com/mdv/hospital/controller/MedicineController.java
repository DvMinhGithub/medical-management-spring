package com.mdv.hospital.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.hospital.dto.request.MedicineRequestDTO;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.MedicineResponseDTO;
import com.mdv.hospital.service.MedicineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicineResponseDTO>> createMedicine(
            @Valid @RequestBody MedicineRequestDTO requestDTO) {
        MedicineResponseDTO responseDTO = medicineService.createMedicine(requestDTO);
        return ResponseEntity.ok(ApiResponse.success(responseDTO, "Thuốc đã được tạo thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicineResponseDTO>>> getAllMedicines() {
        return ResponseEntity.ok(ApiResponse.success(medicineService.getAllMedicines(), "Danh sách thuốc"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMedicine(Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa thuốc thành công"));
    }
}
