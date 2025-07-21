package com.duettsoftware.backend.security;

import com.duettsoftware.backend.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(User user) {
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(secretKey) 
                .compact();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
