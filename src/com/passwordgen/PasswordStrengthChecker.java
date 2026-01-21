package com.passwordgen;

public class PasswordStrengthChecker {

    public String checkStrength(String password) {
        if (password == null || password.isEmpty()) return "WEAK (empty password)";

        int score = 0;

        boolean upper = password.matches(".*[A-Z].*");
        boolean lower = password.matches(".*[a-z].*");
        boolean digit = password.matches(".*[0-9].*");
        boolean symbol = password.matches(".*[^a-zA-Z0-9].*");

        if (upper) score++;
        if (lower) score++;
        if (digit) score++;
        if (symbol) score++;

        if (password.length() >= 8) score++;
        if (password.length() >= 16) score++;

        return switch (score) {
            case 0, 1 -> "WEAK";
            case 2, 3 -> "MEDIUM";
            case 4 -> "GOOD";
            case 5, 6 -> "GREAT";
            default -> "UNKNOWN";
        };
    }
}

