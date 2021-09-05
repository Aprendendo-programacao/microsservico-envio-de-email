package me.gabreuw.microsservicoenviodeemail.adapters.configuration;

import me.gabreuw.microsservicoenviodeemail.MicrosservicoEnvioDeEmailApplication;
import me.gabreuw.microsservicoenviodeemail.application.ports.EmailRepositoryPort;
import me.gabreuw.microsservicoenviodeemail.application.ports.SendEmailServicePort;
import me.gabreuw.microsservicoenviodeemail.application.services.EmailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MicrosservicoEnvioDeEmailApplication.class)
public class BeanConfiguration {

    @Bean
    public EmailServiceImpl emailServiceImpl(
            EmailRepositoryPort repository,
            SendEmailServicePort sendEmailServicePort
    ) {
        return new EmailServiceImpl(repository, sendEmailServicePort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
