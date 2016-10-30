package com.github.nizienko.telegramBot.telegram.bot;

import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by def on 25.10.16.
 */
public interface Receiver {
    void processMessage(Update update);
}
