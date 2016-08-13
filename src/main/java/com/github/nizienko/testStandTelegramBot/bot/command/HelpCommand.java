package com.github.nizienko.testStandTelegramBot.bot.command;

/**
 * Created by def on 13.08.16.
 */
public class HelpCommand implements Command {
    public String execute(String[] args) {
        return "Получить пользователя:\n" +
                "user {host}\n" +
                "example: 'user isla'";
    }

    public String getDescription() {
        return "Помощь";
    }
}
