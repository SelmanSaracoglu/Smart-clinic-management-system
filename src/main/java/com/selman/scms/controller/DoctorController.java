package com.selman.scms.controller;

import com.selman.scms.dto.DoctorAvailableTimeDTO;
import com.selman.scms.service.DoctorAvailableTimeService;
import com.selman.scms.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorAvailableTimeService doctorAvailableTimeService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/user/{userId}/doctor/{doctorId}/availability/{date}")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
            @PathVariable Long userId,
            @PathVariable Long doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String token) {

        String email = tokenService.getUsernameFromToken(token);
        boolean isValid = tokenService.isTokenValid(token, "DOCTOR");

        if (!isValid || email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "status", "error",
                            "message", "Invalid or expired token"
                    ));
        }

        List<DoctorAvailableTimeDTO> availableTimes =
                doctorAvailableTimeService.getAvailabilityForDoctorByDate(doctorId, date);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "userId", userId,
                "email", email,
                "doctorId", doctorId,
                "date", date.toString(),
                "availableTimes", availableTimes
        ));
    }
}
