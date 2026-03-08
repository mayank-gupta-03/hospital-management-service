package com.hospital_management_service.service;

import com.hospital_management_service.dto.InsuranceRequestDto;
import com.hospital_management_service.dto.PatientResponseDto;

public interface InsuranceService {

    PatientResponseDto assignInsurance(InsuranceRequestDto insuranceDto, Long patientId);
    PatientResponseDto disassociateInsurance(Long patientId);

}
