package org.example.service.constants;

public enum ConstantReplyButton {

    CATEGORY_YES_BUTTON("Да"),
    CATEGORY_NO_BUTTON("Нет");
    private String label;


    ConstantReplyButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
