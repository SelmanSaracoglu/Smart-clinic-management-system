
// Spring Data MongoDB’nin @Document anotasyonu ile tanımlanmış

// MongoDB, şema esnekliği sağlar: reçeteler hasta ve duruma göre değişir

// İlaçlar gibi iç içe (nested) yapılar vardır

package com.selman.scms.model;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @NotNull
    private String patientName;

    @NotNull
    private Long appointmentId;

    @NotNull
    private String doctorName;

    private LocalDate dateIssued;

    private List<Medication> medications;

    private String notes;

    private boolean revisitRecommended;

    public static class Medication {
        public String name;
        public String dosage;
        public String frequency;
        public String duration;
    }


    // Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isRevisitRecommended() {
        return revisitRecommended;
    }

    public void setRevisitRecommended(boolean revisitRecommended) {
        this.revisitRecommended = revisitRecommended;
    }
}
