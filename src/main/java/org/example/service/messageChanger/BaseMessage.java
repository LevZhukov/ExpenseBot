package org.example.service.messageChanger;

import org.example.service.CallbackData;
import org.example.service.DBProcessor;
import org.example.service.constants.ConstantReplyText;
import org.example.service.keyboardCreator.KeyboardCreator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public abstract class BaseMessage implements MessageChanger {
    protected final EditMessageText changedMessage;
    protected final DBProcessor dbProcessor;

    public BaseMessage(EditMessageText changedMessage, DBProcessor dbProcessor) {
        this.changedMessage = changedMessage;
        this.dbProcessor = dbProcessor;
    }
}
