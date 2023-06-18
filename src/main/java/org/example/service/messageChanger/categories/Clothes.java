package org.example.service.messageChanger.categories;

import org.example.service.DBProcessor;
import org.example.service.messageChanger.AbstractMessage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component("Одежда")
public class Clothes extends AbstractMessage {
    public Clothes(EditMessageText changedMessage, DBProcessor dbProcessor) {
        super(changedMessage, dbProcessor);
    }
}
