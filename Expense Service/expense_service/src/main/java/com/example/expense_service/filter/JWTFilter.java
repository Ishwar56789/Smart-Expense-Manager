package com.example.expense_service.filter;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTFilter {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }


    private String getTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }


    public Long extractUserID(String authHeader) {
        return extractAllClaims(getTokenFromHeader(authHeader)).get("userId", Long.class);
    }


    public String extractUserEmail(String authHeader) {
        return extractAllClaims(getTokenFromHeader(authHeader)).getSubject();
    }


}
