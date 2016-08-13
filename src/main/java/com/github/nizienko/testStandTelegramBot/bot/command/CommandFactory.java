package com.github.nizienko.testStandTelegramBot.bot.command;

/**
 * Created by def on 13.08.16.
 */
public class CommandFactory {

    public static Command getCommand(String message) {
        if (message.toLowerCase().startsWith("user")) {
            return new GetUserCommand();
        }
        if (message.toLowerCase().startsWith("help")) {
            return new HelpCommand();
        }
        throw new UnknownCommandException();
    }
}
