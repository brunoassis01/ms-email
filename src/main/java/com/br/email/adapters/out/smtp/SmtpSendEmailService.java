package com.br.email.adapters.out.smtp;

import com.br.email.core.domain.Email;
import com.br.email.core.ports.out.SendEmailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpSendEmailService implements SendEmailServicePort {

    @Autowired
    JavaMailSender emailSender;

    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        emailSender.send(message);
    }
}
