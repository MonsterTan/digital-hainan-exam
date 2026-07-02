package com.alibaba.tqn.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class PasswordUtils {


    public static String generateSalt() {
        byte[] salt = new byte[8];
        new SecureRandom().nextBytes(salt);
        return HexFormat.of().formatHex(salt);
    }


    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest((password + salt).getBytes());
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }


    public static boolean verify(String rawPassword, String salt, String hashedPassword) {
        return hashPassword(rawPassword, salt).equals(hashedPassword);
    }
}
