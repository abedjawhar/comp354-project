package com.github.comp354project.model.email;

import com.github.comp354project.utils.Timing;

import javax.mail.MessagingException;
import java.io.File;

public interface IEmailService {
    @Timing
    void sendEmail(String email, String subject, String text, File attachment, String attachmentName) throws MessagingException;
}
