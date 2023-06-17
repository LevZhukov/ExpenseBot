package org.example.service.messageChanger;

import org.example.service.DBProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Component("Медицина")
public class Medicine extends AbstractMessage {
    public Medicine(EditMessageText changedMessage, DBProcessor dbProcessor) {
        super(changedMessage, dbProcessor);
    }
}
