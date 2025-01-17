package com.learning.journal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {

        if (!StringUtils.hasText(to) || !StringUtils.hasText(subject) || !StringUtils.hasText(body)) {
            log.error("Invalid email parameters: to={}, subject={}, body={}", to, subject, body);
            throw new IllegalArgumentException("Email 'to', 'subject', and 'body' cannot be null or empty");
        }
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);

            mailSender.send(mail);

            log.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            log.error("Exception occurred while sending email to {}", to, e);
        }
    }
}
