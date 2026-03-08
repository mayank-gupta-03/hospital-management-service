package com.hospital_management_service.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private Long patientId;
    private Long doctorId;

}
