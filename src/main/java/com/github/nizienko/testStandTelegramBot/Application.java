package com.github.nizienko.testStandTelegramBot;

import com.github.nizienko.testStandTelegramBot.bot.TestStandBot;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by def on 13.08.16.
 */
public class Application {

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TestStandBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
