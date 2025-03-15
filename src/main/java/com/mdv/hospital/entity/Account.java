package com.mdv.hospital.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mdv.hospital.enums.AccountStatus;
import com.mdv.hospital.enums.AccountType;
import com.mdv.hospital.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    @Size(max = 20)
    private String phone;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String fullName;

    @Column(unique = true)
    @Email
    @Size(max = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.INACTIVE;

    @Size(max = 255)
    private String address;

    private LocalDate dateOfBirth;

    @Size(max = 50)
    private String code;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @OneToOne(mappedBy = "admin", fetch = FetchType.LAZY)
    private Facility facility;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Order> history;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> doctorSchedule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    @JsonIgnore
    private Service serviceac;
}
