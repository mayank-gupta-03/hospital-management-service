package com.hospital_management_service;

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
        Insurance insurance = Insurance.builder()
                .policyNumber("LOMBARD_123")
                .provider("ICICI")
                .validUntil(LocalDate.of(2032, 10, 5))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
    }
}
