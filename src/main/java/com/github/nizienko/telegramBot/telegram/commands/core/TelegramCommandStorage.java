package com.github.nizienko.telegramBot.telegram.commands.core;

/**
 * Created by def on 14.08.16.
 */
public interface TelegramCommandStorage {
    void put(Command command);
    Command get(String message);
}
