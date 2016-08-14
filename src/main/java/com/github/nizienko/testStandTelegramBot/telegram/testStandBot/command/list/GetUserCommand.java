package com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.list;

import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.AbstractTelegramCommand;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.BadCommandSyntaxException;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.TelegramCommand;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */

@TelegramCommand
public class GetUserCommand extends AbstractTelegramCommand {

    @Override
    public String getCommandName() {
        return "/user";
    }

    public String execute(Message message) {
        String[] args = message.getText().replaceAll("[\\s]{2,}", " ").split(" ");
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
                "/user {host}\n" +
                "example: 'user isla'";
    }
}
