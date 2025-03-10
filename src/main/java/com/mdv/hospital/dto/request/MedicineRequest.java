package com.mdv.hospital.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineRequest {

    private String barcode;

    @NotBlank(message = "Tên thuốc không được để trống")
    private String name;

    private String description;
}
