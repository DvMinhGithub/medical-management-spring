package com.mdv.hospital.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalFacilityRequestDTO {
    @NotBlank(message = "Địa chỉ không được để trống!")
    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự!")
    private String address;

    @NotBlank(message = "Tên cơ sở y tế không được để trống!")
    @Size(max = 100, message = "Tên cơ sở y tế không được vượt quá 100 ký tự!")
    private String facilityName;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải có 10 chữ số!")
    private String phone;

    @NotBlank(message = "Tên người đứng đầu không được để trống!")
    @Size(max = 100, message = "Tên người đứng đầu không được vượt quá 100 ký tự!")
    private String president;
}
