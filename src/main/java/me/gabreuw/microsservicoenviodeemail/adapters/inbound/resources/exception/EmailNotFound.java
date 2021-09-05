package me.gabreuw.microsservicoenviodeemail.adapters.inbound.resources.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class EmailNotFound extends RuntimeException {

    public EmailNotFound(UUID emailId) {
        super(String.format(
                "Email not found with ID: %s",
                emailId.toString()
        ));
    }
}
