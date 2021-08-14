package me.gabreuw.microsservicoenviodeemail.adapters.inbound.resources;

import lombok.RequiredArgsConstructor;
import me.gabreuw.microsservicoenviodeemail.adapters.inbound.dtos.EmailDTO;
import me.gabreuw.microsservicoenviodeemail.application.entities.EmailModel;
import me.gabreuw.microsservicoenviodeemail.application.services.EmailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class EmailResource {

    private final EmailServiceImpl SERVICE;

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
