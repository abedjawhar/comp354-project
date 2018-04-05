package com.github.comp354project.model.email;

import javax.mail.MessagingException;
import java.io.File;

public interface IEmailService {
    void sendEmail(String email, String subject, String text, File attachment, String attachmentName) throws MessagingException;
}
