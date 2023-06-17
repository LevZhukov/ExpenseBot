package org.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Configuration
public class AppConfiguration {

    @Bean
    ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //frkgjroigj
        //objectMapper.configure();
        return objectMapper;
    }

    @Bean
    @Scope("prototype")
    SendMessage getSendMessage() {

        return new SendMessage();
    }

}
