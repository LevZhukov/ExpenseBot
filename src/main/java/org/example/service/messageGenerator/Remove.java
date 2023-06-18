package org.example.service.messageGenerator;

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

@Component("/remove")
public class Remove implements MessageGenerator {
    @Autowired
    CallbackCreator callbackCreator;
    @Autowired
    ButtonCreator buttonCreator;
    @Autowired
    KeyboardCreator keyboardCreator;
    @Override
    public SendMessage generateMessage(Update update) {

        SendMessage message = new SendMessage();
        message.setText(ConstantReplyText.REMOVE_TEXT.getText());

        CallbackData callbackData1 = callbackCreator.createCallback(ConstantReplyButton.PROCEED_WITH_REMOVE.getLabel(), 0, false);
        CallbackData callbackData2 = callbackCreator.createCallback(ConstantReplyButton.CHECK_NUMBER_BUTTON.getLabel(), 0, false);

        InlineKeyboardButton proceed = buttonCreator.createInlineButton(ConstantReplyButton.PROCEED_WITH_REMOVE.getLabel(), callbackData1.toString());
        InlineKeyboardButton checkNumber = buttonCreator.createInlineButton(ConstantReplyButton.CHECK_NUMBER_BUTTON.getLabel(), callbackData2.toString());

        List<InlineKeyboardButton> listButtons = new ArrayList<>();
        listButtons.add(proceed);
        listButtons.add(checkNumber);

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardCreator.createKeyboard(listButtons);

        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;

    }
}
