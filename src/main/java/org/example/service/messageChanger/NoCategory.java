package org.example.service.messageChanger;

import org.example.service.callbackCreator.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.constants.CategoryButton;
import org.example.service.constants.ConstantReplyText;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("Нет")
public class NoCategory extends AbstractMessage {

    public NoCategory(EditMessageText changedMessage, DBProcessor dbProcessor) {
        super(changedMessage, dbProcessor);
    }
    @Override
    public EditMessageText editMessage(Update update) {

        CallbackData outcomeCallbackData = new CallbackData(update.getCallbackQuery().getData());
        if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), CategoryButton.NO_CATEGORY.getLabel())) {
            changedMessage.setText(ConstantReplyText.SAVED_NO_CATEGORY.getText());
        } else
            changedMessage.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE.getText());
        changedMessage.setReplyMarkup(null);
        return changedMessage;
    }
}
