package com.petclinicrest.services;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendEmail(String address, String subject, String body) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(address);
        mail.setSubject(subject);
        mail.setText(body);
        mail.setSentDate(new Date());
        System.out.println(mail);
        try {
            javaMailSender.send(mail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
