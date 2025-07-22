package com.selman.scms.controller;

import com.selman.scms.dto.DoctorDTO;
import com.selman.scms.model.Appointment;
import com.selman.scms.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

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
}
