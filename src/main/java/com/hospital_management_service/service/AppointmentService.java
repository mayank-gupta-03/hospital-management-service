package com.hospital_management_service.service;

import com.hospital_management_service.dto.AppointmentDto;
import com.hospital_management_service.entity.Appointment;
import com.hospital_management_service.entity.Doctor;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.AppointmentRepository;
import com.hospital_management_service.repository.DoctorRepository;
import com.hospital_management_service.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentDto createAppointment(Appointment newAppointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        if (newAppointment.getId() != null) throw new IllegalArgumentException("Appointment should not have an id!");

        newAppointment.setDoctor(doctor);
        newAppointment.setPatient(patient);
        patient.getAppointments().add(newAppointment);
        doctor.getAppointments().add(newAppointment);

        Appointment appointment = appointmentRepository.save(newAppointment);
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    @Transactional
    public AppointmentDto reassignAppointment(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);

        return modelMapper.map(appointment, AppointmentDto.class);
    }

}
