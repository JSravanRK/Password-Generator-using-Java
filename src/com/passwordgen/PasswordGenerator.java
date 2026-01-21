package com.passwordgen;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>?/";

    private final SecureRandom random = new SecureRandom();

    public String generate(int length, boolean upper, boolean lower, boolean digits, boolean symbols) {

        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4.");
        }

        StringBuilder pool = new StringBuilder();
        List<Character> mustInclude = new ArrayList<>();

        if (upper) {
            pool.append(UPPER);
            mustInclude.add(UPPER.charAt(random.nextInt(UPPER.length())));
        }
        if (lower) {
            pool.append(LOWER);
            mustInclude.add(LOWER.charAt(random.nextInt(LOWER.length())));
        }
        if (digits) {
            pool.append(DIGITS);
            mustInclude.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        if (symbols) {
            pool.append(SYMBOLS);
            mustInclude.add(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }

        if (pool.isEmpty()) {
            throw new IllegalArgumentException("Select at least one character type.");
        }

        if (length < mustInclude.size()) {
            throw new IllegalArgumentException("Length too small for selected character types.");
        }

        StringBuilder password = new StringBuilder();

        // Add must-include chars first
        for (char c : mustInclude) password.append(c);

        // Fill remaining chars
        for (int i = password.length(); i < length; i++) {
            password.append(pool.charAt(random.nextInt(pool.length())));
        }

        // Shuffle password for randomness
        return shuffle(password.toString());
    }

    private String shuffle(String input) {
        char[] arr = input.toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return new String(arr);
    }
}

