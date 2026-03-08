package com.hospital_management_service;

import com.hospital_management_service.dto.AppointmentRequestDto;
import com.hospital_management_service.dto.AppointmentResponseDto;
import com.hospital_management_service.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void createAppointmentTest() {
        AppointmentRequestDto appointmentDto = AppointmentRequestDto
                .builder()
                .appointmentTime(LocalDateTime.of(2026, 4, 4, 1, 30))
                .reason("Routine checkup")
                .build();

        AppointmentResponseDto response = appointmentService.createAppointment(appointmentDto, 1L, 1L);
        assertNotNull(response);
    }

    @Test
    public void reassignAppointmentToNewDoctorTest() {
        AppointmentResponseDto response = appointmentService.reassignAppointment(1L, 3L);
        assertNotNull(response);
    }
}
