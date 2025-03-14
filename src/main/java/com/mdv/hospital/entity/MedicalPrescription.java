package com.mdv.hospital.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_prescription")
public class MedicalPrescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor; // Bác sĩ kê đơn

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User patient; // Bệnh nhân

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment; // Cuộc hẹn liên quan

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionDetail> prescriptionDetails = new ArrayList<>();
}
