package me.gabreuw.microsservicoenviodeemail.application.domain;

import lombok.Data;
import me.gabreuw.microsservicoenviodeemail.application.domain.enums.StatusEmail;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Email {

    private UUID emailId;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmail statusEmail;

}
