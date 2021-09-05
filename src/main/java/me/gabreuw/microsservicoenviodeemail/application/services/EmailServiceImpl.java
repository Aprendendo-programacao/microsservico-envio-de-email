package me.gabreuw.microsservicoenviodeemail.application.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.gabreuw.microsservicoenviodeemail.application.domain.Email;
import me.gabreuw.microsservicoenviodeemail.application.domain.PageInfo;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailRepositoryPort;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailServicePort;
import me.gabreuw.microsservicoenviodeemail.application.ports.SendEmailServicePort;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static me.gabreuw.microsservicoenviodeemail.application.domain.enums.StatusEmail.ERROR;
import static me.gabreuw.microsservicoenviodeemail.application.domain.enums.StatusEmail.SENT;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailServicePort {

    private final EmailRepositoryPort emailRepositoryPort;

    private final SendEmailServicePort sendEmailServicePort;

    @Override
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());

        try {
           sendEmailServicePort.sendEmailSmtp(email);

            email.setStatusEmail(SENT);
        } catch (MailException exception) {
            email.setStatusEmail(ERROR);
            log.error(exception);
        }

        return save(email);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        return emailRepositoryPort.findAll(pageInfo);
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return emailRepositoryPort.findById(emailId);
    }

    @Override
    public Email save(Email email) {
        return emailRepositoryPort.save(email);
    }
}

