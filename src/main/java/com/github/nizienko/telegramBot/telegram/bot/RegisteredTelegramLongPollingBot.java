package com.github.nizienko.telegramBot.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by def on 25.10.16.
 */

public class RegisteredTelegramLongPollingBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(RegisteredTelegramLongPollingBot.class);
    private Telegram telegram;
    private String botName;
    private String botToken;

    public RegisteredTelegramLongPollingBot(Telegram telegram, String botName, String botToken) {
        this.telegram = telegram;
        this.botName = botName;
        this.botToken = botToken;

        final TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        telegram.receive(update);
    }

    public void send(SendMessage sendMessage){
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
