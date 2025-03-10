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
public class MedicalFacilityResponseDTO {

    private String address;

    private String facilityName;

    private String phone;

    private String president;
}
