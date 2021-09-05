package me.gabreuw.microsservicoenviodeemail.adapters.outbound.persistence.entities;

import lombok.Data;
import me.gabreuw.microsservicoenviodeemail.application.domain.enums.StatusEmail;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "EMAIL")
@Data
public class EmailEntity {

    @Id
    @GeneratedValue
    private UUID emailId;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmail statusEmail;

}
