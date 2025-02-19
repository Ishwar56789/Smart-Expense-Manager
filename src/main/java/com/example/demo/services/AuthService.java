package com.example.demo.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    @Value("${jwt.secret.key}")
    private String jwtSecreatKey;

    @Value("${jwt.expiration.time}")
    private long jwtExpirationTime;

    public String generateToken(String registeredUserEmail, long userId) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", userId);

        return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(registeredUserEmail)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                    .and()
                    .signWith(getKey())
                    .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecreatKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
