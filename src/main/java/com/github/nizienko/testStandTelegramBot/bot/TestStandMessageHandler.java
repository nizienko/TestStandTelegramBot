package com.github.nizienko.testStandTelegramBot.bot;

import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */
public class TestStandMessageHandler implements MessageHandler {
    public String process(Message message) {
        return "Hello " + message.getFrom().getFirstName();
    }
}
