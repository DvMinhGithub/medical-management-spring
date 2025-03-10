package com.mdv.hospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponseDTO {
    private Long id;
    private String barcode;
    private String name;
    private String description;
}
