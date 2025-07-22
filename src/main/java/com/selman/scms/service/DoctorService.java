package com.selman.scms.service;

import com.selman.scms.dto.DoctorDTO;
import com.selman.scms.model.Appointment;

import java.util.List;

public interface DoctorService {

    DoctorDTO getDoctorById(Long id);

    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);

    List<Appointment> getAppointmentsForDoctor(Long doctorId);
}
