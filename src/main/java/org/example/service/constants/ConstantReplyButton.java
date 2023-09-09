package org.example.service.constants;

public enum ConstantReplyButton {

    CATEGORY_YES("Да"),
    CATEGORY_NO("Нет"),
    CHECK_NUMBER_BUTTON ("Посмотреть номер"),
    PROCEED_WITH_REMOVE ("Продолжить удаление"),
    REMOVE_ALL ("Удалить все записи");
    private String label;


    ConstantReplyButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
