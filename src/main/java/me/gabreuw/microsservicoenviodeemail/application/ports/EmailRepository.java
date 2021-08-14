package me.gabreuw.microsservicoenviodeemail.application.ports;

import me.gabreuw.microsservicoenviodeemail.application.entities.EmailModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EmailRepository {

    EmailModel save(EmailModel emailModel);

    Page<EmailModel> findAll(Pageable pageable);

    Optional<EmailModel> findById(UUID emailId);

}