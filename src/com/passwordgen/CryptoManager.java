package com.passwordgen;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoManager {

    private static final String ALGO = "AES/CBC/PKCS5Padding";

    // Derive AES key from master password using PBKDF2
    private SecretKeySpec deriveKey(String password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = skf.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    public String encrypt(String plainText, String masterKey) throws Exception {
        SecureRandom sr = new SecureRandom();

        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        byte[] iv = new byte[16];
        sr.nextBytes(iv);

        SecretKeySpec key = deriveKey(masterKey, salt);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        // store salt + iv + encrypted
        return Base64.getEncoder().encodeToString(salt) + ":" +
                Base64.getEncoder().encodeToString(iv) + ":" +
                Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String cipherText, String masterKey) throws Exception {
        String[] parts = cipherText.split(":");
        if (parts.length != 3) throw new IllegalArgumentException("Invalid encrypted data.");

        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] iv = Base64.getDecoder().decode(parts[1]);
        byte[] encrypted = Base64.getDecoder().decode(parts[2]);

        SecretKeySpec key = deriveKey(masterKey, salt);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted);
    }
}

