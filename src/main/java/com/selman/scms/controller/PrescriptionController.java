package com.selman.scms.controller;

import com.selman.scms.dto.PrescriptionDTO;
import com.selman.scms.model.Prescription;
import com.selman.scms.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public Prescription createPrescription(@Valid @RequestBody PrescriptionDTO dto) {
        return prescriptionService.createPrescription(dto);
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable String id) {
        return prescriptionService.getById(id);
    }

    @GetMapping("/patient/{name}")
    public List<Prescription> getByPatientName(@PathVariable String name) {
        return prescriptionService.getByPatientName(name);
    }
}
