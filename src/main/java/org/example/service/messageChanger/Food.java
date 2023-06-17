package org.example.service.messageChanger;

import org.example.service.DBProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component("Продукты")
public class Food extends AbstractMessage {
    public Food(EditMessageText changedMessage, DBProcessor dbProcessor) {
        super(changedMessage, dbProcessor);
    }
}
