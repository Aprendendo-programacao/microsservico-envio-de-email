package me.gabreuw.microsservicoenviodeemail.adapters.outbound.persistence;

import lombok.RequiredArgsConstructor;
import me.gabreuw.microsservicoenviodeemail.application.entities.EmailModel;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Primary
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostgresEmailRepository implements EmailRepository {

    private final SpringDataPostgresEmailRepository EMAIL_REPOSITORY;

    @Override
    public EmailModel save(EmailModel emailModel) {
        return EMAIL_REPOSITORY.save(emailModel);
    }

    @Override
    public Page<EmailModel> findAll(Pageable pageable) {
        return EMAIL_REPOSITORY.findAll(pageable);
    }

    @Override
    public Optional<EmailModel> findById(UUID emailId) {
        return EMAIL_REPOSITORY.findById(emailId);
    }

}
