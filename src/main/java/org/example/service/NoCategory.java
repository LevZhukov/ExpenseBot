package org.example.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component("Нет")
public class NoCategory implements MessageChanger {
    DBProcessor dbProcessor;

    public NoCategory(DBProcessor dbProcessor) {
        this.dbProcessor = dbProcessor;
    }

    @Override
    public EditMessageText editMessage(Update update) {
        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());
        EditMessageText changedMessage = new EditMessageText();

        if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), CategoryButton.NO_CATEGORY.getLabel())) {
            changedMessage.setText(ConstantReplyText.SAVED_NO_CATEGORY.getText());
        } else
            changedMessage.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE.getText());

        return changedMessage;
    }
}
