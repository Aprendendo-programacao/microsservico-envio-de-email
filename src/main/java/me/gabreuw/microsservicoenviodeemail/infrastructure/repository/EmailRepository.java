package me.gabreuw.microsservicoenviodeemail.infrastructure.repository;

import me.gabreuw.microsservicoenviodeemail.domain.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {}