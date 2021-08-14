package me.gabreuw.microsservicoenviodeemail.application.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.gabreuw.microsservicoenviodeemail.application.entities.EmailModel;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailRepository;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static me.gabreuw.microsservicoenviodeemail.application.entities.enums.StatusEmail.ERROR;
import static me.gabreuw.microsservicoenviodeemail.application.entities.enums.StatusEmail.SENT;

@RequiredArgsConstructor
@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

    private final EmailRepository REPOSITORY;
    private final JavaMailSender EMAIL_SENDER;

    @Override
    public EmailModel sendEmail(EmailModel emailModel) {
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
            return REPOSITORY.save(emailModel);
        }
    }

    @Override
    public Page<EmailModel> findAll(Pageable pageable) {
        return REPOSITORY.findAll(pageable);
    }

    @Override
    public Optional<EmailModel> findById(UUID emailId) {
        return REPOSITORY.findById(emailId);
    }
}
