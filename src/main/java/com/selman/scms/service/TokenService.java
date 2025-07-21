package com.selman.scms.service;

public interface TokenService {
    boolean isTokenValid(String token, String requiredRole);
    String getUsernameFromToken(String token);
}