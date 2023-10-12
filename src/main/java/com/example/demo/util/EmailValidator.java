package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class EmailValidator {

        private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        public static boolean isPresentedEmail(String email) {
            if (email == null || email.isEmpty()) {
                return false;
            }

            return true;
        }

        public static boolean isValidEmail(String email) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }
