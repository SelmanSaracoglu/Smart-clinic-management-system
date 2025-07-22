package com.selman.scms.service.impl;

import com.selman.scms.dto.PrescriptionDTO;
import com.selman.scms.model.Medication;
import com.selman.scms.model.Prescription;
import com.selman.scms.repository.PrescriptionRepository;
import com.selman.scms.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription createPrescription(PrescriptionDTO dto) {
        Prescription prescription = new Prescription();
        prescription.setPatientName(dto.getPatientName());
        prescription.setDoctorName(dto.getDoctorName());
        prescription.setAppointmentId(dto.getAppointmentId());
        prescription.setDateIssued(dto.getDateIssued());
        prescription.setNotes(dto.getNotes());
        prescription.setRevisitRecommended(dto.isRevisitRecommended());

        List<Medication> medicationList = new ArrayList<>();
        for (PrescriptionDTO.MedicationDTO medDto : dto.getMedications()) {
            Medication med = new Medication();
            med.setName(medDto.getName());
            med.setDosage(medDto.getDosage());
            med.setDuration(medDto.getDuration());
            medicationList.add(med);
        }

        prescription.setMedications(medicationList);
        return prescriptionRepository.save(prescription);
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Prescription getById(String id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    @Override
    public List<Prescription> getByPatientName(String patientName) {
        return prescriptionRepository.findByPatientName(patientName);
    }
}
