package com.hospital_management_service;

import com.hospital_management_service.dto.InsuranceRequestDto;
import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    InsuranceService insuranceService;

    @Test
    public void createInsuranceTest() {
        InsuranceRequestDto insuranceDto = InsuranceRequestDto.builder()
                .policyNumber("LOMBARD_123")
                .provider("ICICI")
                .validUntil(LocalDate.of(2032, 10, 5))
                .build();

        PatientResponseDto response = insuranceService.assignInsurance(insuranceDto, 1L);
        assertNotNull(response);
    }

    @Test
    public void disassociateInsuranceFromPatientTest() {
        PatientResponseDto response = insuranceService.disassociateInsurance(1L);
        assertNotNull(response);
    }
}
