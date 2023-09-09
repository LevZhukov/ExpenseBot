package org.example.service;

import lombok.extern.log4j.Log4j;
import org.example.service.callbackCreator.CallbackCreator;
import org.example.service.callbackCreator.CallbackData;
import org.example.service.callbackCreator.CallbackFromString;
import org.example.service.keyboardCreator.KeyboardCreator;
import org.example.service.messageChanger.MessageChanger;
import org.example.service.messageGenerator.CustomMessage;
import org.example.service.messageGenerator.MessageGenerator;
import org.example.service.messageGenerator.RemoveExpense;
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

    private final CallbackFromString callbackFromString;

    @Autowired
    private Map<String, MessageGenerator> mapMessage;
    @Autowired
    private Map<String, MessageChanger> mapChangedMessage;
    @Autowired
    private CustomMessage customMessage;
    @Autowired
    private RemoveExpense removeExpense;

    public ExpenseServiceBasic(CallbackFromString callbackFromString) {
        this.callbackFromString = callbackFromString;
    }

    public SendMessage processMessage(Update update) {
        Message incomeMessage = update.getMessage();

        MessageGenerator messageGenerator = mapMessage.get(incomeMessage.getText());
        if(messageGenerator == null && incomeMessage.getText().startsWith("#")){
            messageGenerator = removeExpense;
        }
        if(messageGenerator == null){
            messageGenerator = customMessage;
        }

        SendMessage outcomeMessage = messageGenerator.generateMessage(update);
        outcomeMessage.setChatId(incomeMessage.getChatId().toString());
        return outcomeMessage;
    }

    public EditMessageText processCallbackQuery(Update update) {

        CallbackData incomeCallbackData = callbackFromString.stringToCallback(update.getCallbackQuery().getData());

        MessageChanger messageChanger = mapChangedMessage.get(incomeCallbackData.getCallbackText());
        EditMessageText changedMessage = messageChanger.editMessage(update);

        changedMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        changedMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        return changedMessage;
    }
}
