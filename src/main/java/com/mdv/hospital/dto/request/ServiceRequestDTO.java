package com.mdv.hospital.dto.request;

import jakarta.validation.constraints.Min;
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
public class ServiceRequestDTO {
    private String image;

    @NotBlank(message = "Tên dịch vụ là bắt buộc")
    private String name;

    @NotNull(message = "Giá dịch vụ là bắt buộc")
    @Min(value = 0, message = "Giá dịch vụ không được nhỏ hơn 0")
    private Double price;

    private String description;

    private Long facilityId;
}
