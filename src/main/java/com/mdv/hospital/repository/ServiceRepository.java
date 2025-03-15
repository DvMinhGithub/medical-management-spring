package com.mdv.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mdv.hospital.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT s FROM Service s LEFT JOIN FETCH s.facilitysv")
    List<Service> findAllServicesWithFacility();
}
