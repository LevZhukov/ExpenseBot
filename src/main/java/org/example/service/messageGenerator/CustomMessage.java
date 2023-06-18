package org.example.service.messageGenerator;

import org.example.service.callbackCreator.CallbackCreator;
import org.example.service.callbackCreator.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.buttonCreator.ButtonCreator;
import org.example.service.keyboardCreator.KeyboardCreator;
import org.example.service.constants.ConstantReplyButton;
import org.example.service.constants.ConstantReplyText;
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
    ButtonCreator buttonCreator;
    KeyboardCreator keyboardCreator;
    CallbackCreator callbackCreator;

    public CustomMessage(DBProcessor dbProcessor, KeyboardCreator keyboardCreator, ButtonCreator buttonCreator, CallbackCreator callbackCreator) {
        this.dbProcessor = dbProcessor;
        this.keyboardCreator = keyboardCreator;
        this.buttonCreator = buttonCreator;
        this.callbackCreator = callbackCreator;
    }

    @Override
    public SendMessage generateMessage(Update update) {
        SendMessage outcomeMessage = new SendMessage();
        Integer id = dbProcessor.addRecord(update);
        if (id > 0) {
            outcomeMessage.setText(ConstantReplyText.SAVED_SUM.getText());

            CallbackData callbackData1 = callbackCreator.createCallback(ConstantReplyButton.CATEGORY_YES.getLabel(), id, false);
            CallbackData callbackData2 = callbackCreator.createCallback(ConstantReplyButton.CATEGORY_NO.getLabel(), id, false);

            InlineKeyboardButton button1 = buttonCreator.createInlineButton(ConstantReplyButton.CATEGORY_YES.getLabel(), callbackData1.toString());
            InlineKeyboardButton button2 = buttonCreator.createInlineButton(ConstantReplyButton.CATEGORY_NO.getLabel(), callbackData2.toString());

            List<InlineKeyboardButton> listButtons = new ArrayList<>();
            listButtons.add(button1);
            listButtons.add(button2);

            InlineKeyboardMarkup inlineKeyboardMarkup = keyboardCreator.createKeyboard(listButtons);

            outcomeMessage.setReplyMarkup(inlineKeyboardMarkup);

        } else {
            outcomeMessage.setText(ConstantReplyText.SAVING_FAILURE.getText());
        }

        return outcomeMessage;
    }
}
