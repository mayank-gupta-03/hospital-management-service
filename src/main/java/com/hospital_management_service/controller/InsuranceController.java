package com.hospital_management_service.controller;

import com.hospital_management_service.dto.InsuranceDto;
import com.hospital_management_service.dto.PatientDto;
import com.hospital_management_service.entity.Insurance;
import com.hospital_management_service.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping("/assign/{patientId}")
    public PatientDto assignInsurance(@RequestBody InsuranceDto insuranceDto,
                                     @PathVariable Long patientId) {
        return insuranceService.assignInsurance(insuranceDto, patientId);
    }

    @DeleteMapping("/disassociate/{patientId}")
    public PatientDto disassociateInsurance(@PathVariable Long patientId) {
        return insuranceService.disassociateInsurance(patientId);
    }
}
