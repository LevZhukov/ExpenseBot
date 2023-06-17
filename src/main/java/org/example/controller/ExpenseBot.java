package org.example.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface ExpenseBot {
    public void resetOldMessage(EditMessageText editMessageText);
    public void replyNewMessage(SendMessage message);

}
