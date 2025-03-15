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
public class FacilityRequestDTO {

    @NotBlank(message = "Tên cơ sở y tế là bắt buộc")
    private String facilityName;

    @NotBlank(message = "Địa chỉ là bắt buộc")
    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    private String address;

    @NotBlank(message = "Số điện thoại là bắt buộc")
    private String phone;

    @NotBlank(message = "Tên giám đốc là bắt buộc")
    private String president;
}
