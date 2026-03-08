package com.hospital_management_service.dto;

import lombok.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String specialization;
    private String email;
    private List<AppointmentResponseDto> appointments;
    private Set<DepartmentResponseDto> departments;

}
