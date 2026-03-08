package com.hospital_management_service.controller;

import com.hospital_management_service.dto.AppointmentDto;
import com.hospital_management_service.entity.Appointment;
import com.hospital_management_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment(@RequestBody AppointmentDto appointmentDto,
                                           @RequestParam Long doctorId,
                                           @RequestParam Long patientId) {
        return appointmentService.createAppointment(appointmentDto, doctorId, patientId);
    }

    @PutMapping("/{appointmentId}/reassign")
    public AppointmentDto reassignAppointment(@PathVariable Long appointmentId,
                                             @RequestParam Long doctorId) {
        return appointmentService.reassignAppointment(appointmentId, doctorId);
    }
}
