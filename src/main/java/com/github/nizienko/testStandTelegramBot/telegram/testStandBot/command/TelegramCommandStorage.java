package com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command;

/**
 * Created by def on 14.08.16.
 */
public interface TelegramCommandStorage {
    void put(Command command);
    Command get(String message);
}
