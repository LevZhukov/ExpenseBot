package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class ConstantReplyText {
    static final String START_TEXT = "Я бот - трекер расходов. Я веду историю Ваших трат.\n" +
            "Я умею сохранять, удалять и показывать ваши записи. \n" +
            "\n" +
            "Чтобы начать, просто отправьте мне сообщение с потраченной суммой. \n" +
            "\n" +
            "Сообщение должно содержать только цифры - например, 100";
    static final String SAVED_SUM_TEXT = "Хотите добавить категорию?";
    static final String SAVING_FAILURE_TEXT = "Я могу сохранять только числа с точкой или без точки. Ещё могу удалять записи по Вашей команде, " +
            "для этого используйте опцию /remove из меню.";
    static final String CATEGORY_TEXT = "Выберите категорию:";
}
