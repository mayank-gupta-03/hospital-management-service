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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    private Doctor doctor;
    private Patient patient;
    private Appointment appointment;
    private AppointmentRequestDto requestDto;
    private AppointmentResponseDto responseDto;

    @BeforeEach
    void setUp() {
        doctor = Doctor.builder().id(1L).name("Dr. Smith").appointments(new ArrayList<>()).build();
        patient = Patient.builder().id(1L).name("John Doe").appointments(new ArrayList<>()).build();
        appointment = Appointment.builder().id(1L).appointmentTime(LocalDateTime.now()).doctor(doctor).patient(patient).build();
        requestDto = AppointmentRequestDto.builder().appointmentTime(LocalDateTime.now()).reason("Checkup").build();
        responseDto = AppointmentResponseDto.builder().id(1L).appointmentTime(appointment.getAppointmentTime()).build();
    }

    @Test
    void createAppointment_Success() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(modelMapper.map(requestDto, Appointment.class)).thenReturn(new Appointment());
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
        when(modelMapper.map(appointment, AppointmentResponseDto.class)).thenReturn(responseDto);

        AppointmentResponseDto result = appointmentService.createAppointment(requestDto, 1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    void createAppointment_DoctorNotFound() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appointmentService.createAppointment(requestDto, 1L, 1L));
    }

    @Test
    void reassignAppointment_Success() {
        Doctor newDoctor = Doctor.builder().id(2L).name("Dr. House").appointments(new ArrayList<>()).build();
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(doctorRepository.findById(2L)).thenReturn(Optional.of(newDoctor));
        when(modelMapper.map(appointment, AppointmentResponseDto.class)).thenReturn(responseDto);

        AppointmentResponseDto result = appointmentService.reassignAppointment(1L, 2L);

        assertNotNull(result);
        assertEquals(newDoctor, appointment.getDoctor());
    }

    @Test
    void reassignAppointment_NotFound() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appointmentService.reassignAppointment(1L, 2L));
    }

    @Test
    void getAllAppointments_Success() {
        when(appointmentRepository.findAllByPatientId(1L)).thenReturn(Collections.singletonList(appointment));
        when(modelMapper.map(appointment, AppointmentResponseDto.class)).thenReturn(responseDto);

        List<AppointmentResponseDto> result = appointmentService.getAllAppointments(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(appointmentRepository, times(1)).findAllByPatientId(1L);
    }
}
