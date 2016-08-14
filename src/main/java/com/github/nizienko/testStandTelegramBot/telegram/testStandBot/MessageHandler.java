package com.github.nizienko.testStandTelegramBot.telegram.testStandBot;

import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */
public interface MessageHandler {
    String process(Message message);
}
