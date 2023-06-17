package org.example.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("Да")
public class CategoryChoice implements MessageChanger {

    private final Keyboard keyboard;

    public CategoryChoice(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(Update update) {
        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());
        EditMessageText changedMessage = new EditMessageText();

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        outcomeCallbackData.setCallbackText(CategoryButton.CLOTHES_BUTTON.getLabel());
        buttons.add(keyboard.createInlineButton(CategoryButton.CLOTHES_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.FOOD_BUTTON.getLabel());
        buttons.add(keyboard.createInlineButton(CategoryButton.FOOD_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.MEDICINE_BUTTON.getLabel());
        buttons.add(keyboard.createInlineButton(CategoryButton.MEDICINE_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.RENT_BUTTON.getLabel());
        buttons.add(keyboard.createInlineButton(CategoryButton.RENT_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.OTHER_BUTTON.getLabel());
        buttons.add(keyboard.createInlineButton(CategoryButton.OTHER_BUTTON.getLabel(), outcomeCallbackData.toString()));

        changedMessage.setText(ConstantReplyText.CATEGORY_TEXT.getText());

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.createKeyboard(buttons);

        changedMessage.setReplyMarkup(inlineKeyboardMarkup);

        return changedMessage;
    }
}
