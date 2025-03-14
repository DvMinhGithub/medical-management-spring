package com.mdv.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdv.hospital.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findAllByMedicalFacilityId(Long medicalFacilityId);
}
