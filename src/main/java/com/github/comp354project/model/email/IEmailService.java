package com.github.comp354project.model.email;

public interface IEmailService {
    void sendEmail(String email, String subject, String text);
}
