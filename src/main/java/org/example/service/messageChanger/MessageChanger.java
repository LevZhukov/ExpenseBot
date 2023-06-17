package org.example.service.messageChanger;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageChanger {
    EditMessageText editMessage(Update update);
}
