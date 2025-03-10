package com.mdv.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdv.hospital.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Boolean existsByBarcode(String barcode);
}
