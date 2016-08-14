package com.github.nizienko.testStandTelegramBot.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by def on 14.08.16.
 */
public interface TelegramBot {
    TelegramLongPollingBot getLongPollingBot();
}
