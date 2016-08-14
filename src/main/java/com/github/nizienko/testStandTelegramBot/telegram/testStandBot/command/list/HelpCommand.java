package com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.list;

import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.AbstractTelegramCommand;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.TelegramCommand;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */

@TelegramCommand
public class HelpCommand extends AbstractTelegramCommand {
    @Override
    public String getCommandName() {
        return "/help";
    }

    public String execute(Message message) {
        return "/user - достать/создать пользователя\n" +
                "/host - занять хост\n";
    }

    public String getDescription() {
        return "Помощь";
    }
}
