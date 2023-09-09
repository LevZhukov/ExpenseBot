package org.example.service.messageGenerator;

import org.example.service.DBProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("/show_all")
public class ShowAll implements MessageGenerator {

    @Autowired
    private DBProcessor dbProcessor;
    @Autowired
    private SendMessage message;

    @Override
    public SendMessage generateMessage(Update update) {
        message.setText(dbProcessor.showAll());
        return message;
    }
}