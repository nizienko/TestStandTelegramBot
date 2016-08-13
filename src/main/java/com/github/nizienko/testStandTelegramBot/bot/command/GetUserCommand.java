package com.github.nizienko.testStandTelegramBot.bot.command;

/**
 * Created by def on 13.08.16.
 */
public class GetUserCommand implements Command {
    public String execute(String[] args) {
        if (args.length < 2) {
            throw new BadCommandSyntaxException(this);
        }
        final String host = args[1];
        /*
        реализация получения юзера
         */
        return "Пользователь с хоста " + host;
    }

    public String getDescription() {
        return "Получить пользователя:\n" +
                "user {host}\n" +
                "example: 'user isla'";
    }
}
