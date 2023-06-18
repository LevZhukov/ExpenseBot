package org.example.service.callbackCreator;

import org.example.service.constants.ConstantReplyButton;
import org.springframework.stereotype.Component;

@Component
public class CallbackCreatorBasic implements CallbackCreator {
    @Override
    public CallbackData createCallback(String buttonText, Integer expenseId, Boolean toRemove) {
        return new CallbackData(buttonText, expenseId, false);
    }
}
