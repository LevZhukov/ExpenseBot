package org.example.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardBasic implements Keyboard {

    public InlineKeyboardButton createInlineButton(String text, String callbackData) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(callbackData);
        return inlineKeyboardButton;
    }

    public InlineKeyboardMarkup createKeyboard(List<InlineKeyboardButton> buttonList) {
        List<List<InlineKeyboardButton>> rows = splitToRows(buttonList, 20);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private List<List<InlineKeyboardButton>> splitToRows(List<InlineKeyboardButton> buttonList, int charsInLine) {
        List<List<InlineKeyboardButton>> buttonRowsList = new ArrayList<>();
        int length = 0;
        int rowsCount = -1;
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == 0 || length + buttonList.get(i).getText().length() > charsInLine) {
                List<InlineKeyboardButton> list = new ArrayList<>();
                buttonRowsList.add(list);
                rowsCount++;
                length = 0;
            }
            buttonRowsList.get(rowsCount).add(buttonList.get(i));
            length += buttonList.get(i).getText().length();
        }
        return buttonRowsList;
    }
}
