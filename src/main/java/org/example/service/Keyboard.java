package org.example.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public interface Keyboard {
    public InlineKeyboardButton createInlineButton(String text, String callbackData);
    public InlineKeyboardMarkup createKeyboard(List<InlineKeyboardButton> buttonList);
}
