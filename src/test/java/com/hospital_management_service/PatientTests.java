package com.hospital_management_service;

import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.PatientRepository;
import com.hospital_management_service.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void getPatientByIdTest() {
        PatientResponseDto response = patientService.getPatientById(1L);
        assertNotNull(response);
    }

    @Test
    public void getAllPatientsTest() {
        List<PatientResponseDto> responses = patientService.getAllPatients();
        assertFalse(responses.isEmpty());
    }

}
