package com.github.comp354project.utils;


import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

//import com.github.comp354project.model.user.User;

public class SendMail {

    public static void startMail() {

        //MyMoneyApp official gmail for now
        final String username = "mymoneyappofficial@gmail.com";
        //MyMoneyApp official password for now
        final String password = "MyMoneyApp123";
        //Get it from account user info.
        final String client = "hellolalela@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        // Session is to get in the official gmail
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });



        try {
            //Create the default MimeMessage object.
            Message msg = new MimeMessage(session);

            //Set From: header field
            msg.setFrom(new InternetAddress(username));

            //Set To: Header field
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(client));

            //Set Subject: header field
            msg.setSubject("Statement");

            //Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            //Set the actual message
            messageBodyPart.setText("Here is your statement.");
            // Add the text part
            multipart.addBodyPart(messageBodyPart);

            //This is for attachment part
            MimeBodyPart attachmentBodyPart= new MimeBodyPart();
            //Source of the file
            DataSource source = new FileDataSource("D:\\Users\\sword\\School\\Comp 354\\test.csv"); // ex : "C:\\test.pdf"
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("test.csv"); // ex : "test.pdf"
            //Add the attachment part
            multipart.addBodyPart(attachmentBodyPart);

            msg.setContent(multipart);


            Transport.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}