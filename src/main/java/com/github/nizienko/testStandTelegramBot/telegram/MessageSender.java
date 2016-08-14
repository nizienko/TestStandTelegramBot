package com.github.nizienko.testStandTelegramBot.telegram;

/**
 * Created by def on 15.08.16.
 */
public interface MessageSender {
    void sendMessage(Long chatId, String text);
}
