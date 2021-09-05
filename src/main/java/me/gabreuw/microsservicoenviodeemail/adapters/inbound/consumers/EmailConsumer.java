package me.gabreuw.microsservicoenviodeemail.adapters.inbound.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.gabreuw.microsservicoenviodeemail.adapters.dtos.EmailDTO;
import me.gabreuw.microsservicoenviodeemail.application.domain.Email;
import me.gabreuw.microsservicoenviodeemail.application.services.EmailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailConsumer {

    private final EmailServiceImpl emailServiceImpl;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        Email email = new Email();

        BeanUtils.copyProperties(emailDTO, email);

        emailServiceImpl.sendEmail(email);

        log.info("Email status: {}", email.getStatusEmail().toString());
    }

}
