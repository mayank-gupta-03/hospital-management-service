package com.hospital_management_service.service;

import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;
    private PatientResponseDto responseDto;

    @BeforeEach
    void setUp() {
        patient = Patient.builder().id(1L).name("John Doe").email("john@example.com").build();
        responseDto = PatientResponseDto.builder().id(1L).name("John Doe").email("john@example.com").build();
    }

    @Test
    void getPatientById_Success() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(modelMapper.map(patient, PatientResponseDto.class)).thenReturn(responseDto);

        PatientResponseDto result = patientService.getPatientById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void getPatientById_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> patientService.getPatientById(1L));
    }

    @Test
    void getAllPatients_Success() {
        when(patientRepository.findAllPatients()).thenReturn(Collections.singletonList(patient));
        when(modelMapper.map(patient, PatientResponseDto.class)).thenReturn(responseDto);

        List<PatientResponseDto> result = patientService.getAllPatients();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void getAllPatients_Empty() {
        when(patientRepository.findAllPatients()).thenReturn(Collections.emptyList());

        List<PatientResponseDto> result = patientService.getAllPatients();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
