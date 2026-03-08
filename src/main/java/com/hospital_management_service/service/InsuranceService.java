package com.hospital_management_service.service;

import com.hospital_management_service.dto.PatientDto;
import com.hospital_management_service.entity.Insurance;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.InsuranceRepository;
import com.hospital_management_service.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientDto assignInsurance(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return modelMapper.map(insurance, PatientDto.class);
    }

    @Transactional
    public PatientDto disassociateInsurance(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(null);
        return modelMapper.map(patient, PatientDto.class);
    }

}
