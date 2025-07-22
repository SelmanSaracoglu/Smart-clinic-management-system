package com.selman.scms.controller;

import com.selman.scms.dto.PatientDTO;
import com.selman.scms.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public PatientDTO addPatient(@RequestBody @Valid PatientDTO dto) {
        return service.createPatient(dto);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getById(@PathVariable Long id) {
        return service.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
    }
}
