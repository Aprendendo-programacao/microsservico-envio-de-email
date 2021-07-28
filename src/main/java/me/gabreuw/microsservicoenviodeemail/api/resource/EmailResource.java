package me.gabreuw.microsservicoenviodeemail.api.resource;

import lombok.RequiredArgsConstructor;
import me.gabreuw.microsservicoenviodeemail.api.dto.EmailDTO;
import me.gabreuw.microsservicoenviodeemail.domain.model.EmailModel;
import me.gabreuw.microsservicoenviodeemail.infrastructure.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class EmailResource {

    private final EmailService SERVICE;

    @PostMapping(path = "/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(
            @RequestBody @Valid EmailDTO emailDTO
    ) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);

        SERVICE.sendEmail(emailModel);

        return ResponseEntity
                .status(CREATED)
                .body(emailModel);
    }

}
