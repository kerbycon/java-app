package com.example.demo.user;

public class EmailConfirmDto {

    private String email;

    private String code;

    public EmailConfirmDto(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }
}
