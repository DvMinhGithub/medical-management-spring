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

import com.mdv.hospital.dto.request.MedicineRequest;
import com.mdv.hospital.dto.response.ApiResponse;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicineResponseDTO;
import com.mdv.hospital.service.MedicineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity<ApiResponse<CustomPageResponse<MedicineResponseDTO>>> getAllMedicines(Pageable pageable) {
        CustomPageResponse<MedicineResponseDTO> medicines = medicineService.getAllMedicines(pageable);
        return ResponseEntity.ok(ApiResponse.success(medicines, "Lấy danh sách thuốc thành công!"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MedicineResponseDTO>> createMedicine(
            @Valid @RequestBody MedicineRequest medicineRequest) {
        MedicineResponseDTO medicine = medicineService.createMedicine(medicineRequest);
        return ResponseEntity.ok(ApiResponse.success(medicine, "Tạo thuốc thành công!"));
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<ApiResponse<Void>> deleteMedicine(@PathVariable long medicineId) {
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok(ApiResponse.success("Xóa thuốc thành công!"));
    }
}
