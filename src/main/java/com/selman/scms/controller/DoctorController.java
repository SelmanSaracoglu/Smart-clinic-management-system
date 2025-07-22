package com.selman.scms.controller;

import com.selman.scms.dto.DoctorAvailableTimeDTO;
import com.selman.scms.dto.DoctorDTO;
import com.selman.scms.model.Appointment;
import com.selman.scms.service.DoctorAvailableTimeService;

import com.selman.scms.service.DoctorService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorAvailableTimeService doctorAvailableTimeService;

    // GET /api/doctor/{id}
    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    // PUT /api/doctor/{id}
    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(id, doctorDTO);
    }

    // GET /api/doctor/{id}/appointments
    @GetMapping("/{id}/appointments")
    public List<Appointment> getAppointments(@PathVariable Long id) {
        return doctorService.getAppointmentsForDoctor(id);
    }

    // ✅ GET /api/doctor/{id}/availability?date=2025-07-22
    @GetMapping("/{id}/availability")
    public ResponseEntity<List<DoctorAvailableTimeDTO>> getDoctorAvailability(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<DoctorAvailableTimeDTO> availableTimes =
                doctorAvailableTimeService.getAvailabilityForDoctorByDate(id, date);

        return ResponseEntity.ok(availableTimes);
    }
}
