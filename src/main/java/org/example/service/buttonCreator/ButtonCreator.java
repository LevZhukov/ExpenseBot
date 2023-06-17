package org.example.service.buttonCreator;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface ButtonCreator {
    public InlineKeyboardButton createInlineButton(String text, String callbackData);
}

