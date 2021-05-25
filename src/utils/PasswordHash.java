/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author gustavo
 */
public class PasswordHash {

    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static String generateSalt(final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return null;
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt).toString();
    }

    public static String hashPassword(String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(securePassword).toString();

        } catch (Exception ex) {
            System.err.println("Exception encountered in hashPassword()");
            return null;

        } finally {
            spec.clearPassword();
        }
    }

    public static boolean verifyPassword(String password, String key, String salt) {
        String optEncrypted = hashPassword(password, salt);
        
        return optEncrypted.equals(key);
    }
}
