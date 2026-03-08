package com.hospital_management_service;

import com.hospital_management_service.entity.Appointment;
import com.hospital_management_service.repository.AppointmentRepository;
import com.hospital_management_service.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void createAppointmentTest() {
        Appointment newAppointment = Appointment
                .builder()
                .appointmentTime(LocalDateTime.of(2026, 4, 4, 1, 30))
                .build();

        Appointment appointment = appointmentService.createNewAppointment(newAppointment, 1L, 1L);
    }
}
