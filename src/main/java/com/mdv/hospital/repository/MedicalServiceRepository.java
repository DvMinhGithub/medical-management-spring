package com.mdv.hospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mdv.hospital.entity.MedicalService;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {

    Page<MedicalService> findAllByMedicalFacilityId(Long medicalFacilityId, Pageable pageable);
}
