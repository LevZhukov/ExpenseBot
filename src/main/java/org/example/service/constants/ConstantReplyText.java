package org.example.service.constants;

public enum ConstantReplyText {
    START_TEXT("Я бот - трекер расходов. Я веду историю Ваших трат.\n" +
            "Я умею сохранять, удалять и показывать ваши записи. \n" +
            "\n" +
            "Чтобы начать, просто отправьте мне сообщение с потраченной суммой. \n" +
            "\n" +
            "Сообщение должно содержать только цифры - например, 100"),
    SAVED_SUM_TEXT("Хотите добавить категорию?"),
    SAVING_FAILURE_TEXT("Я могу сохранять только числа с точкой или без точки. Ещё могу удалять записи по Вашей команде, " +
            "для этого используйте опцию /remove из меню."),
    CATEGORY_TEXT("Выберите категорию:"),
    SAVED_NO_CATEGORY("Сохранено без категории."),
    SAVE_CATEGORY_FAILURE("Произошла ошибка, категория не сохранена."),
    CATEGORY_SAVED_TEXT("Cохранено с категорией ");
    private String text;

    ConstantReplyText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
