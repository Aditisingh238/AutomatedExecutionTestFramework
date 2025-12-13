package com.myways.automatedtestexecutionframework.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.recipients}")
    private String recipients;

    public void sendSimpleMail(String subject, String body) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);

        sender.setUsername("madhuritakekar1@gmail.com");
        sender.setPassword("yycixbhcgswijhal");   // Gmail App Password ONLY

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

    }

    public void sendMailWithAttachment(String subject, String body, String attachmentPath)
            throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(from);
        helper.setTo(recipients.split(","));
        helper.setSubject(subject);
        helper.setText(body, false);

        if (attachmentPath != null) {
            File file = new File(attachmentPath);
            if (file.exists()) {
                helper.addAttachment(file.getName(), file);
            } else {
                System.out.println("Attachment NOT FOUND: " + attachmentPath);
            }
        }

        mailSender.send(mimeMessage);
    }

}



/////
//
//package com.myways.automatedtestexecutionframework.service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//import java.io.File;
//import java.util.Properties;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Value("${mail.from}")
//    private String from;
//
//    @Value("${mail.recipients}")
//    private String recipients;
//
//    @Bean
//    public JavaMailSender javaMailSender(
//            @Value("${mail.username}") String username,
//            @Value("${mail.password}") String password
//    ) {
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//
//        sender.setHost("smtp.gmail.com");
//        sender.setPort(587);
//
//        sender.setUsername(username);
//        sender.setPassword(password);
//
//        Properties props = sender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//        return sender;
//    }
//
//
//    public void sendMailWithAttachment(String subject, String body, String attachmentPath)
//            throws MessagingException {
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//        helper.setFrom(from);
//        helper.setTo(recipients.split(","));
//        helper.setSubject(subject);
//        helper.setText(body, false);
//
//        if (attachmentPath != null) {
//            File file = new File(attachmentPath);
//            if (file.exists()) {
//                helper.addAttachment(file.getName(), file);
//            } else {
//                System.out.println("Attachment NOT FOUND: " + attachmentPath);
//            }
//        }
//
//        mailSender.send(mimeMessage);
//    }
//
//    public void sendSimpleMail(String subject, String message) {
//    }
//}
