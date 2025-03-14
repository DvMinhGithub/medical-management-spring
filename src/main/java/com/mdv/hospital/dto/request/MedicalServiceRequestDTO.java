package com.mdv.hospital.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
public class MedicalServiceRequestDTO {

    @NotBlank(message = "Mô tả không được để trống!")
    private String description;

    @NotBlank(message = "Ảnh không được để trống!")
    private String image;

    @NotBlank(message = "Tên dịch vụ không được để trống!")
    private String name;

    @NotNull(message = "Giá không được để trống!")
    private Double price;

    @NotNull(message = "ID cơ sở y tế không được để trống!")
    private Long facilityId;
}
