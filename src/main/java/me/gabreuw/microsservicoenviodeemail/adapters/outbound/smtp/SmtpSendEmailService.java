package me.gabreuw.microsservicoenviodeemail.adapters.outbound.smtp;

import lombok.RequiredArgsConstructor;
import me.gabreuw.microsservicoenviodeemail.application.domain.Email;
import me.gabreuw.microsservicoenviodeemail.application.ports.SendEmailServicePort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmtpSendEmailService implements SendEmailServicePort {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailSmtp(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());

        mailSender.send(message);
    }

}
