package com.mdv.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_result")
public class MedicalResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "level")
    private String level; // Ví dụ: GOOD, NORMAL, BAD

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Appointment appointment; // Cuộc hẹn liên quan

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service; // Dịch vụ

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor; // Bác sĩ thực hiện

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User patient; // Bệnh nhân

    // Constructors, getters, setters
}
