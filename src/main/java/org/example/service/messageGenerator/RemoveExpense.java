package org.example.service.messageGenerator;

import org.example.service.DBProcessor;
import org.example.service.constants.ConstantReplyText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RemoveExpense implements MessageGenerator{
    @Autowired
    private SendMessage message;
    @Autowired
    DBProcessor dbProcessor;
    @Override
    public SendMessage generateMessage(Update update) {
        String expense = update.getMessage().getText().substring(1);
        message.setText(dbProcessor.removeExpense(expense) + expense);
        return message;
    }
}
