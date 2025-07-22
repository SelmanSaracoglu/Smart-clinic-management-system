package com.selman.scms.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    // Geçici sabit key – login ve validate işlemlerinde aynı olmalı
    private final String secretKey = "secret123";

    // Token süresi (örnek: 1 saat)
    private final long expirationMillis = 3600000;

    // ✅ Token oluşturma – ÖDEV KRİTERİ
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    // ✅ Token geçerli mi (rol eşleşiyor mu?)
    public boolean isTokenValid(String token, String requiredRole) {
        try {
            Claims claims = getAllClaims(token);
            String role = claims.get("role", String.class);
            return role != null && role.equalsIgnoreCase(requiredRole);
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Token'dan email veya username al
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            return claims.getSubject(); // Genelde email
        } catch (Exception e) {
            return null;
        }
    }

    // 🔐 Tüm claim'leri al
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
