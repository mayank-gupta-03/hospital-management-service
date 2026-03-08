package com.hospital_management_service.service;

import com.hospital_management_service.dto.InsuranceRequestDto;
import com.hospital_management_service.dto.PatientResponseDto;
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
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PatientResponseDto assignInsurance(InsuranceRequestDto insuranceDto, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        Insurance insurance = modelMapper.map(insuranceDto, Insurance.class);

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return modelMapper.map(insurance, PatientResponseDto.class);
    }

    @Override
    @Transactional
    public PatientResponseDto disassociateInsurance(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(null);

        return modelMapper.map(patient, PatientResponseDto.class);
    }

}
