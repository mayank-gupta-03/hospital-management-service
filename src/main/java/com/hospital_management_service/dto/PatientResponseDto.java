package com.hospital_management_service.dto;

import com.hospital_management_service.entity.type.BloodGroupType;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private BloodGroupType bloodGroup;
    private Long insuranceId;
    private List<AppointmentResponseDto> appointments;

}
