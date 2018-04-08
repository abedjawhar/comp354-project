package com.github.comp354project.model.email;

import com.github.comp354project.utils.Timing;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Timing
public class EmailService implements IEmailService {

    private Session session;
    private String username;
    private String password;

    @Inject
    public EmailService(@Named("email.username") String username, @Named("email.password") String password){
        this.username = username;
        this.password = password;
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Session is to get in the official gmail
        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }


    @Override
    public void sendEmail(String email, String subject, String text, File attachment, String attachmentName) throws MessagingException {
        //Create the default MimeMessage object.
        Message msg = new MimeMessage(session);

        //Set From: header field
        msg.setFrom(new InternetAddress(username));

        //Set To: Header field
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        //Set Subject: header field
        msg.setSubject(subject);

        //Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        //Set the actual message
        messageBodyPart.setText(text);
        // Add the text part
        multipart.addBodyPart(messageBodyPart);

        //This is for attachment part
        MimeBodyPart attachmentBodyPart= new MimeBodyPart();
        //Source of the file
        DataSource source = new FileDataSource(attachment);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(attachmentName); // ex : "test.pdf"
        //Add the attachment part
        multipart.addBodyPart(attachmentBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
