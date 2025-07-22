package com.selman.scms.controller;

import com.selman.scms.dto.PrescriptionDTO;
import com.selman.scms.model.Prescription;
import com.selman.scms.service.PrescriptionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // ✅ Token parametresi eklendi + ResponseEntity ile döndürülüyor
    @PostMapping("/{token}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable String token,
            @Valid @RequestBody PrescriptionDTO dto) {

        // Token doğrulama, loglama, rol kontrolü gibi işlemler burada yapılabilir

        Prescription createdPrescription = prescriptionService.createPrescription(dto);
        return ResponseEntity.ok(createdPrescription);
    }

    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable String id) {
        Prescription prescription = prescriptionService.getById(id);
        return ResponseEntity.ok(prescription);
    }

    @GetMapping("/patient/{name}")
    public ResponseEntity<List<Prescription>> getByPatientName(@PathVariable String name) {
        List<Prescription> prescriptions = prescriptionService.getByPatientName(name);
        return ResponseEntity.ok(prescriptions);
    }
}

