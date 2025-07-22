package com.selman.scms.service;

import com.selman.scms.dto.AppointmentDTO;
import com.selman.scms.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(AppointmentDTO dto);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    void cancelAppointment(Long id);

}


