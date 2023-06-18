package org.example.service.callbackCreator;

public interface CallbackCreator {
    CallbackData createCallback(String buttonText, Integer expenseId, Boolean toRemove);
}
