package com.github.nizienko.testStandTelegramBot.telegram.testStandBot;

import com.github.nizienko.testStandTelegramBot.telegram.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by def on 13.08.16.
 */

@Component
public class TestStandBot extends TelegramLongPollingBot implements TelegramBot {
    private static final Logger LOG = LoggerFactory.getLogger(TestStandBot.class);

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private UsersWhiteList whiteList;

    private String botName;
    private String botToken;

    public TestStandBot(String botName, String botToken){
        this.botName = botName;
        this.botToken = botToken;
    }

    public void onUpdateReceived(Update update) {
        LOG.info(update.toString());
        if(update.hasMessage()){
            Message message = update.getMessage();

            if(message.hasText()){
                if (whiteList.isAllowed(message.getFrom().getId())) {
                    sendMessage(message.getChatId(), messageHandler.process(message));
                }
                else {
                    sendMessage(message.getChatId(), String.format("Нет доступа для id=%d", message.getFrom().getId()));
                }
            }
        }
    }

    public void sendMessage(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString());
        sendMessageRequest.setText(text);
        try {
            sendMessage(sendMessageRequest);
        } catch (TelegramApiException e) {
            LOG.error(e.getMessage());
        }
    }

    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }

    @Override
    public TelegramLongPollingBot getLongPollingBot() {
        return this;
    }
}
