package com.example.demo.util;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        if (password.length() < 8)
            return false;

        boolean hasUpperCase = false;
        boolean hasDigit = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }

            if (hasUpperCase && hasDigit) {
                return true;
            }
        }

        return false;
    }
}
