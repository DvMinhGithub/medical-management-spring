package com.mdv.hospital.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mdv.hospital.dto.request.MedicineRequest;
import com.mdv.hospital.dto.response.CustomPageResponse;
import com.mdv.hospital.dto.response.MedicineResponseDTO;
import com.mdv.hospital.entity.Medicine;
import com.mdv.hospital.exception.ResourceNotFoundException;
import com.mdv.hospital.mapper.MedicineMapper;
import com.mdv.hospital.repository.MedicineRepository;
import com.mdv.hospital.service.MedicineService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    private static final String MSG_MEDICINE_NOT_FOUND = "Không tìm thấy thuốc";
    private static final String MSG_MEDICINE_BARCODE_EXISTS = "Thuốc với mã vạch này đã tồn tại";

    @Override
    public CustomPageResponse<MedicineResponseDTO> getAllMedicines(Pageable pageable) {
        Page<MedicineResponseDTO> medicines =
                medicineRepository.findAll(pageable).map(medicineMapper::toResponse);
        return new CustomPageResponse<>(medicines);
    }

    @Override
    public MedicineResponseDTO getMedicineById(Long id) {
        Medicine medicine = medicineRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG_MEDICINE_NOT_FOUND));
        return medicineMapper.toResponse(medicine);
    }

    @Override
    public MedicineResponseDTO createMedicine(MedicineRequest medicineRequest) {
        Boolean exists = medicineRepository.existsByBarcode(medicineRequest.getBarcode());
        if (exists) {
            throw new ResourceNotFoundException(MSG_MEDICINE_BARCODE_EXISTS);
        }
        Medicine medicine = medicineMapper.toEntity(medicineRequest);
        return medicineMapper.toResponse(medicineRepository.save(medicine));
    }

    @Override
    public void deleteMedicine(Long id) {
        Medicine medicine = medicineRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MSG_MEDICINE_NOT_FOUND));
        medicineRepository.delete(medicine);
    }
}
