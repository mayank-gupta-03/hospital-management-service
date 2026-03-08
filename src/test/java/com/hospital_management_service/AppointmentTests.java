package com.hospital_management_service;

import com.hospital_management_service.dto.AppointmentDto;
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

        AppointmentDto appointment = appointmentService.createAppointment(newAppointment, 1L, 1L);
    }

    @Test
    public void reassignAppointmentToNewDoctorTest() {
        AppointmentDto appointment = appointmentService.reassignAppointment(1L, 3L);
    }
}
