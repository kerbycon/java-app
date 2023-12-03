package com.example.demo.util;

public class PasswordValidator {

    public static boolean isValidPassword(String password) {
        return password.matches("(?=.*[A-Z])(?=.*\\d).{8,}");
    }
}
