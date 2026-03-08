package com.hospital_management_service.service;

import com.hospital_management_service.dto.PatientResponseDto;
import java.util.List;

public interface PatientService {

    PatientResponseDto getPatientById(Long patientId);
    List<PatientResponseDto> getAllPatients();

}
