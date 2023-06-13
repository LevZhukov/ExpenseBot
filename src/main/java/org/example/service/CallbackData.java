package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class CallbackData {
    private String buttonText;
    private Integer expenseId;
    private Boolean toRemove;

    public CallbackData() {
        this.buttonText = buttonText;
        this.expenseId = expenseId;
        this.toRemove = toRemove;
    }

    public CallbackData(String callbackText) {
        String[] strings = callbackText.split("#");
        this.buttonText = strings[0];
        if (strings.length > 1)
            this.expenseId = Integer.parseInt(strings[1]);
        if (strings.length > 2)
            this.toRemove = Boolean.parseBoolean(strings[2]);
    }

    public CallbackData(String buttonText, Integer expenseId, Boolean toRemove) {
        this.buttonText = buttonText;
        this.expenseId = expenseId;
        this.toRemove = toRemove;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(buttonText + "#" + expenseId.toString() + "#" + toRemove.toString());
        return stringBuilder.toString();
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Boolean getToRemove() {
        return toRemove;
    }

    public void setToRemove(Boolean toRemove) {
        this.toRemove = toRemove;
    }
}
