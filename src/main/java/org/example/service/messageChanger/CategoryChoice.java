package org.example.service.messageChanger;

import org.example.service.callbackCreator.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.buttonCreator.ButtonCreator;
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
public class CategoryChoice extends AbstractMessage {
    private final KeyboardCreator keyboardCreator;
    private final ButtonCreator buttonCreator;

    public CategoryChoice(EditMessageText changedMessage, DBProcessor dbProcessor, KeyboardCreator keyboardCreator, ButtonCreator buttonCreator) {
        super(changedMessage, dbProcessor);
        this.keyboardCreator = keyboardCreator;
        this.buttonCreator = buttonCreator;
    }


    @Override
    public EditMessageText editMessage(Update update) {
        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        outcomeCallbackData.setCallbackText(CategoryButton.CLOTHES.getLabel());
        buttons.add(buttonCreator.createInlineButton(CategoryButton.CLOTHES.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.FOOD.getLabel());
        buttons.add(buttonCreator.createInlineButton(CategoryButton.FOOD.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.MEDICINE.getLabel());
        buttons.add(buttonCreator.createInlineButton(CategoryButton.MEDICINE.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.RENT.getLabel());
        buttons.add(buttonCreator.createInlineButton(CategoryButton.RENT.getLabel(), outcomeCallbackData.toString()));
        outcomeCallbackData.setCallbackText(CategoryButton.OTHER.getLabel());
        buttons.add(buttonCreator.createInlineButton(CategoryButton.OTHER.getLabel(), outcomeCallbackData.toString()));

        changedMessage.setText(ConstantReplyText.CATEGORY.getText());

        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardCreator.createKeyboard(buttons);

        changedMessage.setReplyMarkup(inlineKeyboardMarkup);

        return changedMessage;
    }
}
