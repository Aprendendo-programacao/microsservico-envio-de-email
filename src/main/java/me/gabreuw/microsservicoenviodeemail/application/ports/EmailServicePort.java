package me.gabreuw.microsservicoenviodeemail.application.ports;

import me.gabreuw.microsservicoenviodeemail.application.domain.Email;
import me.gabreuw.microsservicoenviodeemail.application.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailServicePort {

    Email sendEmail(Email email);

    List<Email> findAll(PageInfo pageInfo);

    Optional<Email> findById(UUID emailId);

    Email save(Email email);

}
