package com.hospital_management_service.service;

import com.hospital_management_service.dto.AppointmentDto;

public interface AppointmentService {

    AppointmentDto createAppointment(AppointmentDto appointmentDto, Long doctorId, Long patientId);
    AppointmentDto reassignAppointment(Long appointmentId, Long doctorId);

}
