package com.mdv.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mdv.hospital.entity.User;
import com.mdv.hospital.enums.AppointmentStatus;
import com.mdv.hospital.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findAllByRole(UserRole role);

    @Query("SELECT DISTINCT u FROM User u JOIN Appointment a ON u = a.doctor OR u = a.patient WHERE a.status = :status")
    List<User> findAllByAppoinmenStatus(AppointmentStatus status);

    List<User> findAllByServiceId(Long serviceId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByCode(String code);
}
