package com.hospital_management_service.service;

import com.hospital_management_service.dto.InsuranceRequestDto;
import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.entity.Insurance;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.InsuranceRepository;
import com.hospital_management_service.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InsuranceServiceImplTest {

    @Mock
    private InsuranceRepository insuranceRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    private Patient patient;
    private Insurance insurance;
    private InsuranceRequestDto requestDto;
    private PatientResponseDto responseDto;

    @BeforeEach
    void setUp() {
        patient = Patient.builder().id(1L).name("John Doe").build();
        insurance = Insurance.builder().id(1L).policyNumber("POL123").provider("Provider").validUntil(LocalDate.now().plusYears(1)).build();
        requestDto = InsuranceRequestDto.builder().policyNumber("POL123").provider("Provider").validUntil(LocalDate.now().plusYears(1)).build();
        responseDto = PatientResponseDto.builder().id(1L).name("John Doe").insuranceId(1L).build();
    }

    @Test
    void assignInsurance_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(modelMapper.map(requestDto, Insurance.class)).thenReturn(insurance);
        when(modelMapper.map(insurance, PatientResponseDto.class)).thenReturn(responseDto);

        PatientResponseDto result = insuranceService.assignInsurance(requestDto, 1L);

        assertNotNull(result);
        assertEquals(insurance, patient.getInsurance());
        assertEquals(patient, insurance.getPatient());
    }

    @Test
    void assignInsurance_PatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.assignInsurance(requestDto, 1L));
    }

    @Test
    void disassociateInsurance_Success() {
        patient.setInsurance(insurance);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(modelMapper.map(patient, PatientResponseDto.class)).thenReturn(responseDto);

        PatientResponseDto result = insuranceService.disassociateInsurance(1L);

        assertNotNull(result);
        assertNull(patient.getInsurance());
    }

    @Test
    void disassociateInsurance_PatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> insuranceService.disassociateInsurance(1L));
    }
}
