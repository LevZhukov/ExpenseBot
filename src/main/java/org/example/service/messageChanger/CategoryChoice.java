package org.example.service.messageChanger;

import org.example.service.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.keyboardCreator.KeyboardCreator;
import org.example.service.constants.CategoryButton;
import org.example.service.constants.ConstantReplyText;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component("Да")
public class CategoryChoice extends BaseMessage {
    private final KeyboardCreator keyboardCreator;

    public CategoryChoice(EditMessageText changedMessage, DBProcessor dbProcessor, KeyboardCreator keyboardCreator) {
        super(changedMessage, dbProcessor);
        this.keyboardCreator = keyboardCreator;
    }


    @Override
    public EditMessageText editMessage(Update update) {
        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        outcomeCallbackData.setCallbackText(CategoryButton.CLOTHES_BUTTON.getLabel());
        buttons.add(keyboardCreator.createInlineButton(CategoryButton.CLOTHES_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.FOOD_BUTTON.getLabel());
        buttons.add(keyboardCreator.createInlineButton(CategoryButton.FOOD_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.MEDICINE_BUTTON.getLabel());
        buttons.add(keyboardCreator.createInlineButton(CategoryButton.MEDICINE_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.RENT_BUTTON.getLabel());
        buttons.add(keyboardCreator.createInlineButton(CategoryButton.RENT_BUTTON.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.OTHER_BUTTON.getLabel());
        buttons.add(keyboardCreator.createInlineButton(CategoryButton.OTHER_BUTTON.getLabel(), outcomeCallbackData.toString()));

        changedMessage.setText(ConstantReplyText.CATEGORY_TEXT.getText());

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardCreator.createKeyboard(buttons);

        changedMessage.setReplyMarkup(inlineKeyboardMarkup);

        return changedMessage;
    }
}
