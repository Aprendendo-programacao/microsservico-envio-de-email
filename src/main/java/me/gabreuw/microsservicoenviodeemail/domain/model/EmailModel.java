package me.gabreuw.microsservicoenviodeemail.domain.model;

import lombok.Data;
import me.gabreuw.microsservicoenviodeemail.domain.enums.StatusEmail;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tb_email")
public class EmailModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long emailId;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime sendDateEmail;

    private StatusEmail statusEmail;

}
