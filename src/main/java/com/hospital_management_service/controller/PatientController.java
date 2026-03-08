package com.hospital_management_service.controller;

import com.hospital_management_service.dto.AppointmentResponseDto;
import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.service.AppointmentService;
import com.hospital_management_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @GetMapping
    public List<PatientResponseDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientId}")
    public PatientResponseDto getPatientById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @GetMapping("/{patientId}/appointments")
    public List<AppointmentResponseDto> getAllAppointments(@PathVariable Long patientId) {
        return appointmentService.getAllAppointments(patientId);
    }

}
