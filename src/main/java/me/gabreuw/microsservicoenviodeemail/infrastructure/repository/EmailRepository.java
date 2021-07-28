package me.gabreuw.microsservicoenviodeemail.infrastructure.repository;

import me.gabreuw.microsservicoenviodeemail.domain.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {}