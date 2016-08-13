package com.github.nizienko.testStandTelegramBot.bot.command;

/**
 * Created by def on 13.08.16.
 */
public class BadCommandSyntaxException extends IllegalArgumentException {
    public BadCommandSyntaxException(Command command) {
        super(command.getDescription());
    }
}
