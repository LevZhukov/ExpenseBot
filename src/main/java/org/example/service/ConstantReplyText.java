package org.example.controller;

import org.springframework.stereotype.Component;

@Component
public class ConstantReplyText {
    static final String START_TEXT = "Я бот - трекер расходов. Я веду историю Ваших трат.\n" +
            "Я умею сохранять, удалять и показывать ваши записи. \n" +
            "\n" +
            "Чтобы начать, просто отправьте мне сообщение с потраченной суммой. \n" +
            "\n" +
            "Сообщение должно содержать только цифры - например, 100";
}
