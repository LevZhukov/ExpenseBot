package org.example.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ExpenseServiceInterface {
    public SendMessage processMessage(Update update);

    public EditMessageText processCallbackQuery(Update update);

}
