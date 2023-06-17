package org.example.service.constants;

public enum CategoryButton {

    FOOD_BUTTON("Продукты"),
    CLOTHES_BUTTON("Одежда"),
    RENT_BUTTON("Аренда"),
    MEDICINE_BUTTON("Медицина"),
    OTHER_BUTTON("Другое"),
    NO_CATEGORY("Без категории");
    private String label;

    CategoryButton(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
