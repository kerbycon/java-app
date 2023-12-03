package com.example.demo.util;

public class CodeValidator {

    public static boolean containsOnlyDigits(String input) {
        return input.matches("\\d+");
    }
}
