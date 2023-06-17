package org.example.service.messageChanger;

import org.example.service.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.constants.ConstantReplyText;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("Одежда")
public class Clothes extends BaseMessage {
    public Clothes(EditMessageText changedMessage, DBProcessor dbProcessor) {
        super(changedMessage, dbProcessor);
    }

    @Override
    public EditMessageText editMessage(Update update) {
        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());

        if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), outcomeCallbackData.getCallbackText())) {
            changedMessage.setText(ConstantReplyText.CATEGORY_SAVED_TEXT.getText() + outcomeCallbackData.getCallbackText());
        } else
            changedMessage.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE.getText());
        changedMessage.setReplyMarkup(null);

        return changedMessage;
    }
}
