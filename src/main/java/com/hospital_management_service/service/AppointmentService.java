package com.hospital_management_service.service;

import com.hospital_management_service.dto.AppointmentRequestDto;
import com.hospital_management_service.dto.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentDto, Long doctorId, Long patientId);
    AppointmentResponseDto reassignAppointment(Long appointmentId, Long doctorId);
    List<AppointmentResponseDto> getAllAppointments(Long patientId);

}
