package com.selman.scms.controller;

import com.selman.scms.model.Admin;
import com.selman.scms.model.Doctor;
import com.selman.scms.repository.AdminRepository;
import com.selman.scms.repository.DoctorRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private DoctorRepository doctorRepository;

    private final String secretKey = "secret123"; // TokenServiceImpl ile aynı

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByUsername(username);


        System.out.println("Kullanıcı adı: " + username);
        System.out.println("Şifre: " + password);


        if (optionalDoctor.isEmpty()) {
            return "Invalid username";
        }

        Doctor doctor = optionalDoctor.get();

        System.out.println("Veritabanındaki şifre: " + doctor.getPassword());

        if (!doctor.getPassword().equals(password)) {
            return "Invalid password";
        }

        // Token üret
        return Jwts.builder()
                .setSubject(doctor.getEmail())
                .claim("role", "DOCTOR")
                .signWith(SignatureAlgorithm.HS256, "secret123".getBytes())
                .compact();
    }

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String username, @RequestParam String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);

        if (optionalAdmin.isEmpty()) {
            return "Invalid username";
        }

        Admin admin = optionalAdmin.get();

        if (!admin.getPassword().equals(password)) {
            return "Invalid password";
        }

        return Jwts.builder()
                .setSubject(admin.getEmail())
                .claim("role", "ADMIN")
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }


}
