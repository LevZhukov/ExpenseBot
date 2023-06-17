package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/show_all")
public class ShowAll implements MessageGenerator {

    @Autowired
    DBProcessor dbProcessor;

    @Override
    public SendMessage generateMessage(Update update) {
        SendMessage message = new SendMessage();
        message.setText(dbProcessor.showAll());
        return message;
    }
}