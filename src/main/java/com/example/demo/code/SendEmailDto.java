package com.example.demo.code;

public class SendEmailDto {

    private String to;

    private String subject;

    private String text;

    public SendEmailDto(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
