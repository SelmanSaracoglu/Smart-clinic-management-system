package com.selman.scms.service;

import com.selman.scms.dto.DoctorAvailableTimeDTO;
import com.selman.scms.model.Doctor;
import com.selman.scms.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorAvailableTimeService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorAvailableTimeDTO> getAvailabilityForDoctorByDate(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with ID: " + doctorId));

        List<DoctorAvailableTimeDTO> result = new ArrayList<>();

        DayOfWeek dayOfWeek = date.getDayOfWeek(); // örnek: MONDAY

        for (String entry : doctor.getAvailableTimes()) {
            // örnek entry: "Monday 10-12"
            if (entry.toLowerCase().startsWith(dayOfWeek.name().toLowerCase())) {
                String[] parts = entry.split(" ", 2);
                if (parts.length == 2) {
                    result.add(new DoctorAvailableTimeDTO(parts[0], parts[1]));
                }
            }
        }

        return result;
    }
}
