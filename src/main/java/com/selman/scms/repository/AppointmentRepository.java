package com.selman.scms.repository;

import com.selman.scms.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorDoctorId(Long doctorId);

}
