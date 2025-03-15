package com.mdv.hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacilityResponseDTO {

    private String facilityName;

    private String address;

    private String phone;

    private String president;
}
