package com.selman.scms.controller;

import com.selman.scms.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/adminDashboard/{token}")
    public String adminDashboard(@PathVariable String token, Model model) {
        if (tokenService.isTokenValid(token, "ADMIN")) {
            model.addAttribute("username", tokenService.getUsernameFromToken(token));
            return "adminDashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/doctorDashboard/{token}")
    public String doctorDashboard(@PathVariable String token, Model model) {
        if (tokenService.isTokenValid(token, "DOCTOR")) {
            model.addAttribute("username", tokenService.getUsernameFromToken(token));
            return "doctorDashboard";
        }
        return "redirect:/login";
    }
}

