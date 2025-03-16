package com.mdv.hospital.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineRequestDTO {
    @NotBlank(message = "Tên thuốc là bắt buộc")
    @Size(max = 255, message = "Tên thuốc không được vượt quá 255 ký tự")
    private String name;

    @Size(max = 50, message = "Mã vạch không được vượt quá 50 ký tự")
    private String barcode;

    private String describemedicine;
}
