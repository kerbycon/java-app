package com.example.demo.util;

public class CodeValidator {

    public static boolean containsOnlyDigits(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
