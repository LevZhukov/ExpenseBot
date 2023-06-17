package org.example.service.constants;

public enum CategoryButton {

    FOOD("Продукты"),
    CLOTHES("Одежда"),
    RENT("Аренда"),
    MEDICINE("Медицина"),
    OTHER("Другое"),
    NO_CATEGORY("Без категории");
    private String label;

    CategoryButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
