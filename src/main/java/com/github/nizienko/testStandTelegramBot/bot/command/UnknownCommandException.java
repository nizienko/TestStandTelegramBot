package com.github.nizienko.testStandTelegramBot.bot.command;

/**
 * Created by def on 13.08.16.
 */
public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException() {
        super("Не известная команда");
    }
}
