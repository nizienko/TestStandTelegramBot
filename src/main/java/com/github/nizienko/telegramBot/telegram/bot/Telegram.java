package com.github.nizienko.telegramBot.telegram.bot;

import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by def on 24.10.16.
 */
public interface Telegram {
    void sendTextMessage(Long id, String message);
    void subscribeOnMessages(Receiver receiver);
    void receive(Update update);
}
