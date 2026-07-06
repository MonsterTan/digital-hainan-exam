package com.alibaba.tqn.common.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtils implements InitializingBean {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer:digital-hainan-exam}")
    private String issuer;

    @Value("${jwt.expiration-hours:2}")
    private int expirationHours;

    private Algorithm algorithm;
    private JWTVerifier verifier;

    @Override
    public void afterPropertiesSet() {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();
    }

    public String generateToken(Long userId, String username) {
        Date now = new Date();
        long expirationMs = (long) expirationHours * 60 * 60 * 1000;
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(String.valueOf(userId))
            .withClaim("username", username)
            .withIssuedAt(now)
            .withExpiresAt(new Date(now.getTime() + expirationMs))
            .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.warn("Token verification failed: {}", e.getMessage());
            return null;
        }
    }

    public Long getUserId(DecodedJWT jwt) {
        return Long.parseLong(jwt.getSubject());
    }

    public String getUsername(DecodedJWT jwt) {
        return jwt.getClaim("username").asString();
    }
}
