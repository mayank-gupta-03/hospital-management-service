package com.hospital_management_service.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceRequestDto {

    private String policyNumber;
    private String provider;
    private LocalDate validUntil;

}
