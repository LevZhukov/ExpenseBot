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
    private CallbackCreator callbackCreator;
    @Autowired
    private ButtonCreator buttonCreator;
    @Autowired
    private KeyboardCreator keyboardCreator;
    @Autowired
    private SendMessage message;
    @Override
    public SendMessage generateMessage(Update update) {

        message.setText(ConstantReplyText.REMOVE_TEXT.getText());

        CallbackData callbackData1 = callbackCreator.createCallback(ConstantReplyButton.CHECK_NUMBER_BUTTON.getLabel(), 0, false);
        CallbackData callbackData2 = callbackCreator.createCallback(ConstantReplyButton.REMOVE_ALL.getLabel(), 0, false);

        InlineKeyboardButton checkNumber = buttonCreator.createInlineButton(ConstantReplyButton.CHECK_NUMBER_BUTTON.getLabel(), callbackData1.toString());
        InlineKeyboardButton removeAll = buttonCreator.createInlineButton(ConstantReplyButton.REMOVE_ALL.getLabel(), callbackData2.toString());

        List<InlineKeyboardButton> listButtons = new ArrayList<>();
        listButtons.add(checkNumber);
        listButtons.add(removeAll);

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardCreator.createKeyboard(listButtons);

        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;

    }
}
