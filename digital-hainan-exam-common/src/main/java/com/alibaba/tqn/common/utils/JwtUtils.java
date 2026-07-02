package com.alibaba.tqn.common.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtils {

    private static final String SECRET = "digital-hainan-exam-jwt-secret-key";
    private static final String ISSUER = "digital-hainan-exam";
    // Token expires in 2 hours
    private static final long EXPIRATION_MS = 2 * 60 * 60 * 1000L;

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM)
        .withIssuer(ISSUER)
        .build();

    /**
     * Generate token with userId and username in claims
     */
    public static String generateToken(Long userId, String username) {
        Date now = new Date();
        return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(String.valueOf(userId))
            .withClaim("username", username)
            .withIssuedAt(now)
            .withExpiresAt(new Date(now.getTime() + EXPIRATION_MS))
            .sign(ALGORITHM);
    }

    /**
     * Verify and decode token, returns null on failure
     */
    public static DecodedJWT verifyToken(String token) {
        try {
            return VERIFIER.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * Extract userId from DecodedJWT
     */
    public static Long getUserId(DecodedJWT jwt) {
        return Long.parseLong(jwt.getSubject());
    }

    /**
     * Extract username from DecodedJWT
     */
    public static String getUsername(DecodedJWT jwt) {
        return jwt.getClaim("username").asString();
    }
}
