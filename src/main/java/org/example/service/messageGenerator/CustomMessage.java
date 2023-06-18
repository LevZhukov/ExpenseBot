package org.example.service.messageGenerator;

import org.example.service.DBProcessor;
import org.example.service.buttonCreator.ButtonCreator;
import org.example.service.callbackCreator.CallbackCreator;
import org.example.service.callbackCreator.CallbackData;
import org.example.service.constants.ConstantReplyButton;
import org.example.service.constants.ConstantReplyText;
import org.example.service.keyboardCreator.KeyboardCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomMessage implements MessageGenerator {
    @Autowired
    DBProcessor dbProcessor;
    @Autowired
    ButtonCreator buttonCreator;
    @Autowired
    KeyboardCreator keyboardCreator;
    @Autowired
    CallbackCreator callbackCreator;

    @Override
    public SendMessage generateMessage(Update update) {
        SendMessage outcomeMessage = new SendMessage();
        Integer id = dbProcessor.addRecord(update);
        if (id > 0) {
            outcomeMessage.setText(ConstantReplyText.SAVED_SUM.getText());

            CallbackData callbackDataYes = callbackCreator.createCallback(ConstantReplyButton.CATEGORY_YES.getLabel(), id, false);
            CallbackData callbackDataNo = callbackCreator.createCallback(ConstantReplyButton.CATEGORY_NO.getLabel(), id, false);

            InlineKeyboardButton buttonYes = buttonCreator.createInlineButton(ConstantReplyButton.CATEGORY_YES.getLabel(), callbackDataYes.toString());
            InlineKeyboardButton buttonNo = buttonCreator.createInlineButton(ConstantReplyButton.CATEGORY_NO.getLabel(), callbackDataNo.toString());

            List<InlineKeyboardButton> listButtons = new ArrayList<>();
            listButtons.add(buttonYes);
            listButtons.add(buttonNo);

            InlineKeyboardMarkup inlineKeyboardMarkup = keyboardCreator.createKeyboard(listButtons);

            outcomeMessage.setReplyMarkup(inlineKeyboardMarkup);

        } else {
            outcomeMessage.setText(ConstantReplyText.SAVING_FAILURE.getText());
        }

        return outcomeMessage;
    }
}
