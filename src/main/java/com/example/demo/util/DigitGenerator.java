package com.example.demo.util;

import java.util.Random;

public class DigitGenerator {

    private static final Random random = new Random();

    public static String generateSixDigitString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }
}
