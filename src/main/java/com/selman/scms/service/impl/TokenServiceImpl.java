package com.selman.scms.service.impl;

import com.selman.scms.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    // Geçici bir sabit key – login tarafında aynı key kullanılmalı
    private final String secretKey = "secret123";

    @Override
    public boolean isTokenValid(String token, String requiredRole) {
        try {
            Claims claims = getAllClaims(token);
            String role = claims.get("role", String.class);
            return role != null && role.equalsIgnoreCase(requiredRole);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            return claims.getSubject(); // Genellikle email veya username burada olur
        } catch (Exception e) {
            return null;
        }
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}

