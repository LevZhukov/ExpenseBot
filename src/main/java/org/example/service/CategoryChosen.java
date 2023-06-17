package org.example.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component()
public class CategoryChosen implements MessageChanger {
    @Override
    public EditMessageText editMessage(Update update) {
        return null;
    }
}
