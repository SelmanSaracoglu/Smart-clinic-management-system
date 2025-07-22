package com.selman.scms.service;

import com.selman.scms.dto.AppointmentDTO;
import com.selman.scms.model.Appointment;
import com.selman.scms.model.Doctor;
import com.selman.scms.model.Patient;
import com.selman.scms.repository.AppointmentRepository;
import com.selman.scms.repository.DoctorRepository;
import com.selman.scms.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // ✅ as per assignment instruction
    public Appointment bookAppointment(AppointmentDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());

        return appointmentRepository.save(appointment); // <--- required direct call
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
