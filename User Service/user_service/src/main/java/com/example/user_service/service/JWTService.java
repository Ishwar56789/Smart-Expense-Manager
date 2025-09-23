package com.example.user_service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${jwt.expiration.time}")
    private long jwtExpirationInMs;


    public String generateJwtToken(String userEmail, Long userId) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", userId);

        return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(userEmail)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                    .and()
                    .signWith(getKey())
                    .compact();
    }


    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    }


    public String extractUserEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims); 
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractUserEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
