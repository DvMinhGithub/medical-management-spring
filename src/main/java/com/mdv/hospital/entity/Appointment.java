package com.mdv.hospital.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status; // Ví dụ: PENDING, DONE, CANCELLED

    @Column(name = "test_date")
    private LocalDateTime testDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor; // Bác sĩ thực hiện

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User patient; // Bệnh nhân

    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    private MedicalPrescription prescription; // Đơn thuốc

    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    private MedicalFacility facility; // Cơ sở y tế
}
