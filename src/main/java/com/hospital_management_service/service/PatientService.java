package com.hospital_management_service.service;

import com.hospital_management_service.dto.PatientDto;
import java.util.List;

public interface PatientService {

    PatientDto getPatientById(Long patientId);
    List<PatientDto> getAllPatients();

}
