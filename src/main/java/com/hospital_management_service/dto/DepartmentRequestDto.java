package com.hospital_management_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

    private String name;
    private Long headDoctorId;

}
