package com.hospital_management_service.service;

import com.hospital_management_service.entity.Insurance;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.InsuranceRepository;
import com.hospital_management_service.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return patient;
    }

}
