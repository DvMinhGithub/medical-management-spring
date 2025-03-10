package com.mdv.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdv.hospital.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {}
