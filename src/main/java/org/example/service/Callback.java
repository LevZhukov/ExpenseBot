package org.example.service;

import org.springframework.stereotype.Service;

@Service
public abstract class Callback {
    protected String buttonText;
    protected Integer expenseId;
    protected Boolean toRemove;

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
