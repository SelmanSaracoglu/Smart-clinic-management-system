package com.selman.scms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {

    @NotBlank
    private String patientName;

    @NotNull
    private Long appointmentId;

    @NotBlank
    private String doctorName;

    @NotNull
    private LocalDate dateIssued;

    @NotEmpty
    private List<MedicationDTO> medications;

    private String notes;

    private boolean revisitRecommended;

    // MedicationDTO (inner class)
    public static class MedicationDTO {

        @NotBlank
        private String name;

        @NotBlank
        private String dosage;

        @NotBlank
        private String duration;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }

    // Getters and Setters for PrescriptionDTO
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

    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
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
