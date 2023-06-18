package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Configuration
public class AppConfiguration {

    @Bean
    EditMessageText editMessageText(){
        return new EditMessageText();
    }

    @Bean
    @Scope("prototype")
    SendMessage getSendMessage() {
        return new SendMessage();
    }
}
