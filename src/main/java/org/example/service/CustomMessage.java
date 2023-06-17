package org.example.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomMessage implements MessageGenerator {

    DBProcessor dbProcessor;
    Keyboard keyboard;

    public CustomMessage(DBProcessor dbProcessor, Keyboard keyboard) {
        this.dbProcessor = dbProcessor;
        this.keyboard = keyboard;
    }

    @Override
    public SendMessage generateMessage(Update update) {
        SendMessage message = processCustomTextMessage(update);

        return message;
    }

    public SendMessage processCustomTextMessage(Update update) {
        SendMessage outcomeMessage = new SendMessage();
        Integer id = dbProcessor.addRecord(update);
        if (id > 0) {
            outcomeMessage.setText(ConstantReplyText.SAVED_SUM_TEXT.getText());

            CallbackData callbackData1 = new CallbackData(ConstantReplyButton.CATEGORY_YES_BUTTON.getLabel(), id, false);
            CallbackData callbackData2 = new CallbackData(ConstantReplyButton.CATEGORY_NO_BUTTON.getLabel(), id, false);
            InlineKeyboardButton button1 = keyboard.createInlineButton(ConstantReplyButton.CATEGORY_YES_BUTTON.getLabel(), callbackData1.toString());
            InlineKeyboardButton button2 = keyboard.createInlineButton(ConstantReplyButton.CATEGORY_NO_BUTTON.getLabel(), callbackData2.toString());

            List<InlineKeyboardButton> listButtons = new ArrayList<>();
            listButtons.add(button1);
            listButtons.add(button2);

            InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.createKeyboard(listButtons);

            outcomeMessage.setReplyMarkup(inlineKeyboardMarkup);
        } else {
            outcomeMessage.setText(ConstantReplyText.SAVING_FAILURE_TEXT.getText());
        }

        return outcomeMessage;
    }
}
