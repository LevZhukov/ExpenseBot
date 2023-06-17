package org.example.service.messageChanger;

import org.example.service.DBProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component("Аренда")
public class Rent extends AbstractMessage {
    public Rent(EditMessageText changedMessage, DBProcessor dbProcessor) {
        super(changedMessage, dbProcessor);
    }

}
