package org.example.service.constants;

public enum ConstantReplyButton {

    CATEGORY_YES("Да"),
    CATEGORY_NO("Нет");
    private String label;


    ConstantReplyButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
