package com.hospital_management_service.service;

import com.hospital_management_service.dto.AppointmentRequestDto;
import com.hospital_management_service.dto.AppointmentResponseDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AppointmentResponseDto createAppointment(AppointmentRequestDto appointmentDto, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        Appointment newAppointment = modelMapper.map(appointmentDto, Appointment.class);

        newAppointment.setDoctor(doctor);
        newAppointment.setPatient(patient);
        patient.getAppointments().add(newAppointment);
        doctor.getAppointments().add(newAppointment);

        Appointment appointment = appointmentRepository.save(newAppointment);
        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Override
    @Transactional
    public AppointmentResponseDto reassignAppointment(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);

        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Override
    @Transactional
    public List<AppointmentResponseDto> getAllAppointments(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);

        List<AppointmentResponseDto> appointmentResponseDtos = new ArrayList<>();
        appointments.forEach(appointment -> appointmentResponseDtos.add(modelMapper.map(appointment, AppointmentResponseDto.class)));

        return appointmentResponseDtos;
    }

}
