package com.hospital_management_service.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {

    private LocalDateTime appointmentTime;
    private String reason;

}
