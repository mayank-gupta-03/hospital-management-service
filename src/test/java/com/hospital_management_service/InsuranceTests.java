package com.hospital_management_service;

import com.hospital_management_service.dto.InsuranceDto;
import com.hospital_management_service.dto.PatientDto;
import com.hospital_management_service.entity.Insurance;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    InsuranceService insuranceService;

    @Test
    public void createInsuranceTest() {
        InsuranceDto insuranceDto = InsuranceDto.builder()
                .policyNumber("LOMBARD_123")
                .provider("ICICI")
                .validUntil(LocalDate.of(2032, 10, 5))
                .build();

        insuranceService.assignInsurance(insuranceDto, 1L);
    }

    @Test
    public void disassociateInsuranceFromPatientTest() {
        insuranceService.disassociateInsurance(1L);
    }
}
