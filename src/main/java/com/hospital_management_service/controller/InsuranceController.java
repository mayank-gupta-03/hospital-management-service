package com.hospital_management_service.controller;

import com.hospital_management_service.dto.InsuranceRequestDto;
import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping("/assign/{patientId}")
    public PatientResponseDto assignInsurance(@RequestBody InsuranceRequestDto insuranceDto,
                                     @PathVariable Long patientId) {
        return insuranceService.assignInsurance(insuranceDto, patientId);
    }

    @DeleteMapping("/disassociate/{patientId}")
    public PatientResponseDto disassociateInsurance(@PathVariable Long patientId) {
        return insuranceService.disassociateInsurance(patientId);
    }
}
