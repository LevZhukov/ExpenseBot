package org.example.controller;

import lombok.extern.log4j.Log4j;
import org.example.service.ExpenseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Log4j
public class ExpenseBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    private final ExpenseService expenseService;

    public ExpenseBot(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            log.debug(update.getMessage().getText());
            SendMessage outcomeMessage = expenseService.processMessage(update);
            replyNewMessage(outcomeMessage);
        }
        if (update.hasCallbackQuery()) {
            log.debug(update.getCallbackQuery().getData());
            System.out.println("Callback data " + update.getCallbackQuery().getData());

            EditMessageText editMessageText = expenseService.processCallbackQuery(update);
            replyChangeOldMessage((editMessageText));
        }
    }

    public void replyNewMessage(SendMessage message) {
        try {
            execute(message);
            log.debug(message.getText());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


    private void replyChangeOldMessage(EditMessageText editMessageText) {

        try {
            execute(editMessageText);
            log.debug(editMessageText.getText());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}
