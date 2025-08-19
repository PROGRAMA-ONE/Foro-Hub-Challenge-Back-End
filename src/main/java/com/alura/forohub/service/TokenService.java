package com.alura.forohub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private final Algorithm algorithm;
    private final long expirationMs;
    private final JWTVerifier verifier;

    public TokenService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationMs
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.expirationMs = expirationMs;
        this.verifier = JWT.require(this.algorithm).withIssuer("forohub").build();
    }

    public String generarToken(UserDetails user) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return JWT.create()
                .withIssuer("forohub")
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);
    }

    public String extraerUsername(String token) {
        DecodedJWT decoded = verifier.verify(token);
        return decoded.getSubject();
    }

    public boolean esValido(String token, UserDetails user) {
        String subject = extraerUsername(token);
        return user != null && user.getUsername().equals(subject);
    }
}
