package org.example.service.messageGenerator;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageGenerator {
    SendMessage generateMessage(Update update);
}
