package org.example.service.callbackCreator;

import org.springframework.stereotype.Component;

@Component
public class CallbackData extends AbstractCallback {

    public CallbackData() {

    }

    public CallbackData(String buttonText, Integer expenseId, Boolean toRemove) {
        this.callbackText = buttonText;
        this.expenseId = expenseId;
        this.toRemove = toRemove;
    }

    public CallbackData(String callbackText) {
        String[] strings = callbackText.split("#");
        this.callbackText = strings[0];
        if (strings.length > 1)
            this.expenseId = Integer.parseInt(strings[1]);
        if (strings.length > 2)
            this.toRemove = Boolean.parseBoolean(strings[2]);
    }
}
