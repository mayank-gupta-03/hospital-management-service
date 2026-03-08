package com.hospital_management_service.dto;

import lombok.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDto {
    private Long id;
    private String name;
    private Long headDoctorId;
    private Set<Long> doctorIds;
}
