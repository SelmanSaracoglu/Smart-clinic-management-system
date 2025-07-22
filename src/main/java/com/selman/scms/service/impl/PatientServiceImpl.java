package com.selman.scms.service.impl;

import com.selman.scms.dto.PatientDTO;
import com.selman.scms.model.Patient;
import com.selman.scms.repository.PatientRepository;
import com.selman.scms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setPatientId(patient.getPatientId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setEmail(patient.getEmail());
        dto.setPhone(patient.getPhone());
        return dto;
    }

    private Patient convertToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        return patient;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient saved = repository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public void deletePatient(Long id) {
        repository.deleteById(id);
    }
}

