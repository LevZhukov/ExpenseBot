package org.example.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Service
@Log4j
public class ExpenseServiceBasic implements ExpenseServiceInterface {

    private final DBProcessor dbProcessor;
    private final CallbackData callbackData;
    private final Keyboard keyboard;

    @Autowired
    private Map<String, MessageGenerator> mapMessage;
    @Autowired
    private Map<String, MessageChanger> mapChangedMessage;
    @Autowired
    private CustomMessage customMessage;

    public ExpenseServiceBasic(DBProcessor dbProcessor, CallbackData callbackData, Keyboard keyboard) {
        this.dbProcessor = dbProcessor;
        this.callbackData = callbackData;
        this.keyboard = keyboard;
    }

    public SendMessage processMessage(Update update) {
        Message incomeMessage = update.getMessage();

        MessageGenerator messageGenerator = mapMessage.get(incomeMessage.getText());
        messageGenerator = messageGenerator == null ? customMessage : messageGenerator;

        SendMessage outcomeMessage = messageGenerator.generateMessage(update);
        outcomeMessage.setChatId(incomeMessage.getChatId().toString());
        return outcomeMessage;
    }

    public EditMessageText processCallbackQuery(Update update) {

        String incomeCallbackDataText = update.getCallbackQuery().getData();
        CallbackData incomeCallbackData = new  CallbackData(update.getCallbackQuery().getData());

        MessageChanger messageChanger = mapChangedMessage.get(incomeCallbackData.getCallbackText());

        EditMessageText changedMessage = messageChanger.editMessage(update);

        changedMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        changedMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

//            switch (outcomeCallbackData.getButtonText()) {
//
//
//            case ConstantReplyButton.CLOTHES_BUTTON:
//            case ConstantReplyButton.FOOD_BUTTON:
//            case ConstantReplyButton.MEDICINE_BUTTON:
//            case ConstantReplyButton.RENT_BUTTON:
//            case ConstantReplyButton.NO_CATEGORY:
//                if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), outcomeCallbackData.getButtonText())) {
//                    changedMessage.setText(ConstantReplyText.CATEGORY_SAVED_TEXT + outcomeCallbackData.getButtonText());
//                } else
//                    changedMessage.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE.getText());
//                break;
//        }
        return changedMessage;
    }
}
