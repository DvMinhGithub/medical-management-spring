package com.mdv.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdv.hospital.entity.MedicalService;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {

    List<MedicalService> findAllByMedicalFacilityId(Long medicalFacilityId);
}
