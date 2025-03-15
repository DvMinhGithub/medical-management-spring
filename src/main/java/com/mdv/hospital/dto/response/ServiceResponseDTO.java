package com.mdv.hospital.dto.response;

import com.mdv.hospital.entity.Facility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDTO {
    private long id;
    private String image;
    private String name;
    private Double price;
    private String description;
    private Facility facility;
}
