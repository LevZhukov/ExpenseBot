package org.example.service;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j
public class ExpenseServiceBasic implements ExpenseServiceInterface {
    private final DBProcessor dbProcessor;
    private final CallbackData callbackData;
    private final Keyboard keyboard;

    public ExpenseServiceBasic(DBProcessor dbProcessor, CallbackData callbackData, Keyboard keyboard) {
        this.dbProcessor = dbProcessor;
        this.callbackData = callbackData;
        this.keyboard = keyboard;
    }

    public SendMessage processPreDefinedMessage(Update update) {
        Message incomeMessage = update.getMessage();
        SendMessage outcomeMessage = new SendMessage();
        outcomeMessage.setChatId(incomeMessage.getChatId().toString());
        switch (incomeMessage.getText()) {
            case "/start":
                outcomeMessage.setText(ConstantReplyText.START_TEXT);
                break;
            case "/show_all":
                outcomeMessage.setText(dbProcessor.showAll());
                break;

            default:
                outcomeMessage = processCustomTextMessage(update);
        }
        outcomeMessage.setChatId(update.getMessage().getChatId().toString());
        return outcomeMessage;
    }

    public SendMessage processCustomTextMessage(Update update) {
        SendMessage outcomeMessage = new SendMessage();
        Integer id = dbProcessor.addRecord(update);
        if (id > 0) {
            outcomeMessage.setText(ConstantReplyText.SAVED_SUM_TEXT);

            CallbackData callbackData1 = new CallbackData(ConstantReplyButton.CATEGORY_YES_BUTTON_TEXT, id, false);
            CallbackData callbackData2 = new CallbackData(ConstantReplyButton.CATEGORY_NO_BUTTON_TEXT, id, false);
            InlineKeyboardButton button1 = keyboard.createInlineButton(ConstantReplyButton.CATEGORY_YES_BUTTON_TEXT, callbackData1.toString());
            InlineKeyboardButton button2 = keyboard.createInlineButton(ConstantReplyButton.CATEGORY_NO_BUTTON_TEXT, callbackData2.toString());

            List<InlineKeyboardButton> listButtons = new ArrayList<>();
            listButtons.add(button1);
            listButtons.add(button2);

            InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.createKeyboard(listButtons);

            outcomeMessage.setReplyMarkup(inlineKeyboardMarkup);
        } else {
            outcomeMessage.setText(ConstantReplyText.SAVING_FAILURE_TEXT);
        }

        return outcomeMessage;
    }

    public EditMessageText processCallbackQuery(Update update) {

        String incomeCallbackData = update.getCallbackQuery().getData();
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());

        CallbackData outcomeCallbackData = new CallbackData(incomeCallbackData);
        switch (outcomeCallbackData.getButtonText()) {
            case ConstantReplyButton.CATEGORY_YES_BUTTON_TEXT:
                outcomeCallbackData.setButtonText(ConstantCategories.CLOTHES_BUTTON);

                List<InlineKeyboardButton> buttons = new ArrayList<>();
                outcomeCallbackData.setButtonText(ConstantCategories.CLOTHES_BUTTON);
                buttons.add(keyboard.createInlineButton(ConstantCategories.CLOTHES_BUTTON, outcomeCallbackData.toString()));
                outcomeCallbackData.setButtonText(ConstantCategories.FOOD_BUTTON);
                buttons.add(keyboard.createInlineButton(ConstantCategories.FOOD_BUTTON, outcomeCallbackData.toString()));
                outcomeCallbackData.setButtonText(ConstantCategories.MEDICINE_BUTTON);
                buttons.add(keyboard.createInlineButton(ConstantCategories.MEDICINE_BUTTON, outcomeCallbackData.toString()));
                outcomeCallbackData.setButtonText(ConstantCategories.RENT_BUTTON);
                buttons.add(keyboard.createInlineButton(ConstantCategories.RENT_BUTTON, outcomeCallbackData.toString()));
                outcomeCallbackData.setButtonText(ConstantCategories.OTHER_BUTTON);
                buttons.add(keyboard.createInlineButton(ConstantCategories.OTHER_BUTTON, outcomeCallbackData.toString()));

                editMessageText.setText(ConstantReplyText.CATEGORY_TEXT);

                InlineKeyboardMarkup inlineKeyboardMarkup = keyboard.createKeyboard(buttons);

                editMessageText.setReplyMarkup(inlineKeyboardMarkup);

                break;
            case ConstantReplyButton.CATEGORY_NO_BUTTON_TEXT:
                if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), ConstantCategories.NO_CATEGORY)) {
                    editMessageText.setText(ConstantReplyText.SAVED_NO_CATEGORY);
                } else
                    editMessageText.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE);
                break;

            case ConstantCategories.CLOTHES_BUTTON:
            case ConstantCategories.FOOD_BUTTON:
            case ConstantCategories.MEDICINE_BUTTON:
            case ConstantCategories.RENT_BUTTON:
            case ConstantCategories.OTHER_BUTTON:
                if (dbProcessor.categoryUpdated(outcomeCallbackData.getExpenseId(), outcomeCallbackData.getButtonText())) {
                    editMessageText.setText(ConstantReplyText.CATEGORY_SAVED_TEXT + outcomeCallbackData.getButtonText());
                } else
                    editMessageText.setText(ConstantReplyText.SAVE_CATEGORY_FAILURE);
                break;

        }
        return editMessageText;
    }
}
