package org.example.service.keyboardCreator;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardCreatorBasic implements KeyboardCreator {
    private Integer numOfCharsInOneLine = 20;

    public void setNumOfCharsInOneLine(Integer numOfCharsInOneLine) {
        this.numOfCharsInOneLine = numOfCharsInOneLine;
    }

    public KeyboardCreatorBasic(){
    }
    public KeyboardCreatorBasic(Integer numOfCharsInOneLine) {
        this.numOfCharsInOneLine = numOfCharsInOneLine;
    }

    public InlineKeyboardMarkup createKeyboard(List<InlineKeyboardButton> buttonList) {
        List<List<InlineKeyboardButton>> buttonRows = splitToRows(buttonList, numOfCharsInOneLine);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(buttonRows);
        return inlineKeyboardMarkup;
    }

    private List<List<InlineKeyboardButton>> splitToRows(List<InlineKeyboardButton> buttons, int charsInLine) {
        List<List<InlineKeyboardButton>> buttonRows = new ArrayList<>();
        int length = 0;
        int rowsCount = -1;
        for (int i = 0; i < buttons.size(); i++) {
            if (i == 0 || length + buttons.get(i).getText().length() > charsInLine) {
                List<InlineKeyboardButton> list = new ArrayList<>();
                buttonRows.add(list);
                rowsCount++;
                length = 0;
            }
            buttonRows.get(rowsCount).add(buttons.get(i));
            length += buttons.get(i).getText().length();
        }
        return buttonRows;
    }
}
