package com.hospital_management_service.service;

import com.hospital_management_service.dto.InsuranceDto;
import com.hospital_management_service.dto.PatientDto;

public interface InsuranceService {

    PatientDto assignInsurance(InsuranceDto insuranceDto, Long patientId);
    PatientDto disassociateInsurance(Long patientId);

}
