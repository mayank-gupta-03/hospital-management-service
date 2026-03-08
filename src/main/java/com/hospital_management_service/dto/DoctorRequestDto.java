package com.hospital_management_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {
    private String name;
    private String specialization;
    private String email;
}
