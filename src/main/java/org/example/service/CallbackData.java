package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class CallbackData extends Callback {

    public CallbackData() {
        this.buttonText = buttonText;
        this.expenseId = expenseId;
        this.toRemove = toRemove;
    }

    public CallbackData(String buttonText, Integer expenseId, Boolean toRemove) {
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
}
