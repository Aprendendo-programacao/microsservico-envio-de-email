package me.gabreuw.microsservicoenviodeemail.application.ports;

import me.gabreuw.microsservicoenviodeemail.application.domain.Email;

public interface SendEmailServicePort {

    void sendEmailSmtp(Email email);

}
