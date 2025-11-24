package com.myways.automatedtestexecutionframework.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.recipients}")
    private String recipients;

    public void sendSimpleMail(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipients.split(","));
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendMailWithAttachment(String subject, String body, String attachmentPath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(from);
        helper.setTo(recipients.split(","));
        helper.setSubject(subject);
        helper.setText(body);

        if (attachmentPath != null) {
            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            if (file.exists()) {
                helper.addAttachment(file.getFilename(), file);
            }
        }
        mailSender.send(mimeMessage);
    }
}
