package com.mercato.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-expiration-ms}")
    private long accessExpMs;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpMs;

    public String generateAccessToken(String subject, String role) {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(subject)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessExpMs))
                .sign(alg);
    }

    public String generateRefreshToken(String subject) {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpMs))
                .sign(alg);
    }

    public DecodedJWT validateToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        return JWT.require(alg).build().verify(token);
    }

    public String getSubject(String token) {
        DecodedJWT jwt = validateToken(token);
        return jwt.getSubject();
    }
}
