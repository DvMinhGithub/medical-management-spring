package com.mdv.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdv.hospital.entity.Medicine;

@Repository
public interface MedicineRespository extends JpaRepository<Medicine, Long> {}
