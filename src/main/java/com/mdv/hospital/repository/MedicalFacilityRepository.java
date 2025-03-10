package com.mdv.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdv.hospital.entity.MedicalFacility;

public interface MedicalFacilityRepository extends JpaRepository<MedicalFacility, Long> {}
