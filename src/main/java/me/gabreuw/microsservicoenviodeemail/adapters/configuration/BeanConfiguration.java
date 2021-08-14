package me.gabreuw.microsservicoenviodeemail.adapters.configuration;

import me.gabreuw.microsservicoenviodeemail.MicrosservicoEnvioDeEmailApplication;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailRepository;
import me.gabreuw.microsservicoenviodeemail.application.services.EmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@ComponentScan(basePackageClasses = MicrosservicoEnvioDeEmailApplication.class)
public class BeanConfiguration {

    @Bean
    public EmailServiceImpl emailServiceImpl(EmailRepository repository, JavaMailSender emailSender) {
        return new EmailServiceImpl(repository, emailSender);
    }

}
