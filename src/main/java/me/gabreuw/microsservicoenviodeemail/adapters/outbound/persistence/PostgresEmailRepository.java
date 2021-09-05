package me.gabreuw.microsservicoenviodeemail.adapters.outbound.persistence;

import lombok.RequiredArgsConstructor;
import me.gabreuw.microsservicoenviodeemail.adapters.outbound.persistence.entities.EmailEntity;
import me.gabreuw.microsservicoenviodeemail.application.domain.Email;
import me.gabreuw.microsservicoenviodeemail.application.domain.PageInfo;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
@RequiredArgsConstructor
public class PostgresEmailRepository implements EmailRepositoryPort {

    private final SpringDataPostgresEmailRepository postgresEmailRepository;
    private final ModelMapper modelMapper;

    @Override
    public Email save(Email email) {
        EmailEntity savedEmail = postgresEmailRepository.save(
                modelMapper.map(
                        email,
                        EmailEntity.class
                )
        );

        return modelMapper.map(savedEmail, Email.class);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        Pageable pageConfiguration = PageRequest.of(
                pageInfo.getPageNumber(),
                pageInfo.getPageSize()
        );

        return postgresEmailRepository
                .findAll(pageConfiguration)
                .stream()
                .map(emailEntity -> modelMapper.map(
                        emailEntity,
                        Email.class
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return postgresEmailRepository
                .findById(emailId)
                .map(emailEntity -> modelMapper.map(
                        emailEntity,
                        Email.class
                ));
    }

}
