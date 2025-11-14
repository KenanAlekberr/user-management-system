package com.example.usermanagement.service.abstraction.auth;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}