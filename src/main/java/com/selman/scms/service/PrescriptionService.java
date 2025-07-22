package com.selman.scms.service;

import com.selman.scms.dto.PrescriptionDTO;
import com.selman.scms.model.Prescription;

import java.util.List;

public interface PrescriptionService {

    Prescription createPrescription(PrescriptionDTO dto);
    List<Prescription> getAllPrescriptions();
    Prescription getById(String id);
    List<Prescription> getByPatientName(String patientName);
}
