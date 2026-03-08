package com.hospital_management_service.service;

import com.hospital_management_service.dto.PatientResponseDto;
import com.hospital_management_service.entity.Patient;
import com.hospital_management_service.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient with id: " + patientId + " not found"));
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAllPatients();
        List<PatientResponseDto> patientDtos = new ArrayList<>();
        patients.forEach(patient -> {
            patientDtos.add(modelMapper.map(patient, PatientResponseDto.class));
        });
        return patientDtos;
    }
}
