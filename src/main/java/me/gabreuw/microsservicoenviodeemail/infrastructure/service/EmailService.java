package me.gabreuw.microsservicoenviodeemail.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.gabreuw.microsservicoenviodeemail.domain.model.EmailModel;
import me.gabreuw.microsservicoenviodeemail.infrastructure.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static me.gabreuw.microsservicoenviodeemail.domain.enums.StatusEmail.ERROR;
import static me.gabreuw.microsservicoenviodeemail.domain.enums.StatusEmail.SENT;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class EmailService {

    private final EmailRepository REPOSITORY;
    private final JavaMailSender EMAIL_SENDER;

    public void sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            EMAIL_SENDER.send(message);

            emailModel.setStatusEmail(SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(ERROR);
            log.error(e);
        } finally {
            REPOSITORY.save(emailModel);
        }
    }
}
