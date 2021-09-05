package me.gabreuw.microsservicoenviodeemail.adapters.inbound.resources;

import lombok.RequiredArgsConstructor;
import me.gabreuw.microsservicoenviodeemail.adapters.dtos.EmailDTO;
import me.gabreuw.microsservicoenviodeemail.adapters.inbound.resources.exception.EmailNotFound;
import me.gabreuw.microsservicoenviodeemail.application.domain.Email;
import me.gabreuw.microsservicoenviodeemail.application.domain.PageInfo;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class EmailResource {

    private final EmailServicePort emailService;

    @PostMapping(path = "/sending-email")
    public ResponseEntity<Email> sendingEmail(
            @RequestBody @Valid EmailDTO emailDTO
    ) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);

        emailService.sendEmail(email);

        return ResponseEntity
                .status(CREATED)
                .body(email);
    }

    @GetMapping(path = "/emails")
    public ResponseEntity<Page<Email>> findAllEmails(
            @PageableDefault(
                    size = 5,
                    sort = "emailId",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);

        List<Email> emails = emailService.findAll(pageInfo);

        PageImpl<Email> responsePage = new PageImpl<>(emails, pageable, emails.size());

        return ResponseEntity
                .ok()
                .body(responsePage);
    }

    @GetMapping(path = "/emails/{emailId}")
    public ResponseEntity<Email> findEmailById(@PathVariable UUID emailId) {
        Email email = emailService
                .findById(emailId)
                .orElseThrow(() -> new EmailNotFound(emailId));

        return ResponseEntity
                .ok()
                .body(email);
    }

}
