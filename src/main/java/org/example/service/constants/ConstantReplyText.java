package org.example.service.constants;

public enum ConstantReplyText {
    START("Я бот - трекер расходов. Я веду историю Ваших трат.\n" +
            "Я умею сохранять, удалять и показывать ваши записи. \n" +
            "\n" +
            "Чтобы начать, просто отправьте мне сообщение с потраченной суммой. \n" +
            "\n" +
            "Сообщение должно содержать только цифры - например, 100"),
    SAVED_SUM("Хотите добавить категорию?"),
    SAVING_FAILURE("Я могу сохранять только числа с точкой или без точки. Ещё могу удалять записи по Вашей команде, " +
            "для этого используйте опцию /remove из меню."),
    CATEGORY("Выберите категорию:"),
    SAVED_NO_CATEGORY("Сохранено без категории."),
    SAVE_CATEGORY_FAILURE("Произошла ошибка, категория не сохранена."),
    CATEGORY_SAVED("Cохранено с категорией "),
    REMOVE_TEXT("Будьте осторожны, записи невозможно восстановить после удаления.\n \n" +
            "Вы можете удалять записи по одной или  все сразу.\n \n" +
            "Для удаления всех записей воспользуйтесь кнопкой внизу.\n \n" +
            "Для удаления одной записи, необходимо отправить сообщением # и её номер без пробелов. " +
            "Например, #3 или #28\n \n" +
            "Если не знаете номер записи для удаления, можно посмотреть его в списке."),
    EXPENSE_REMOVED("Удалена запись о расходах с номером "),
    EXPENSE_NOT_FOUND("Не найдена запись о расходах с номером "),
    NO_EXPESNES("Не найдено записей о расходах");

    private String text;

    ConstantReplyText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
