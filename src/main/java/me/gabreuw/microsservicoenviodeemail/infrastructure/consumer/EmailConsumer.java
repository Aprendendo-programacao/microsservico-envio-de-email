package me.gabreuw.microsservicoenviodeemail.infrastructure.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.gabreuw.microsservicoenviodeemail.api.dto.EmailDTO;
import me.gabreuw.microsservicoenviodeemail.domain.model.EmailModel;
import me.gabreuw.microsservicoenviodeemail.infrastructure.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        EmailModel emailModel = new EmailModel();

        BeanUtils.copyProperties(emailDTO, emailModel);

        emailService.sendEmail(emailModel);

        log.info("Email status: {}", emailModel.getStatusEmail().toString());
    }

}
