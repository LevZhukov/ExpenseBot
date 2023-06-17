package org.example.service.messageChanger;

import org.example.service.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.constants.ConstantReplyText;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public abstract class BaseMessage implements MessageChanger {
    protected final EditMessageText changedMessage;
    protected final DBProcessor dbProcessor;

    protected BaseMessage(EditMessageText changedMessage, DBProcessor dbProcessor) {
        this.changedMessage = changedMessage;
        this.dbProcessor = dbProcessor;
    }

    public EditMessageText editMessage(Update update) {
        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());
        if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), outcomeCallbackData.getCallbackText())) {
            changedMessage.setText(ConstantReplyText.CATEGORY_SAVED.getText() + outcomeCallbackData.getCallbackText());
        } else
            changedMessage.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE.getText());
        changedMessage.setReplyMarkup(null);
        return changedMessage;
    }
}
