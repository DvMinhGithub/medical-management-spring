package com.mdv.hospital.dto.response;

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
public class ServiceResponseDTO {

    private Long id;

    private String description;

    private String image;

    private String name;

    private Double price;

    private Long facilityId;
}
