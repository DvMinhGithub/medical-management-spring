package com.mdv.hospital.dto.response;

import java.time.LocalDateTime;

import com.mdv.hospital.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private long id;
    private LocalDateTime createdAt;
    private String note;
    private LocalDateTime testDate;
    private OrderStatus status;
    private Long patientId;
    private Long prescriptionId;
}
