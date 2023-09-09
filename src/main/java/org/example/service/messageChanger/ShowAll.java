package org.example.service.messageChanger;

import org.example.service.DBProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component("Посмотреть номер")
public class ShowAll implements MessageChanger{
    @Autowired
    private DBProcessor dbProcessor;
    @Autowired
    private EditMessageText message;
    @Override
    public EditMessageText editMessage(Update update) {
        message.setText(dbProcessor.showAll());
        return message;
    }
}
