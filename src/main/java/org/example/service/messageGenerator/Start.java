package org.example.service.messageGenerator;

import org.example.service.constants.ConstantReplyText;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component("/start")
public class Start implements MessageGenerator {
    @Override
    public SendMessage generateMessage(Update update) {
        SendMessage message = new SendMessage();
        message.setText(ConstantReplyText.START_TEXT.getText());
        return message;
    }
}
