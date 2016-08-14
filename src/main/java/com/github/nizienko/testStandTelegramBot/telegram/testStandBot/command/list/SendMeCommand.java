package com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.list;

import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.AbstractTelegramCommand;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.TelegramCommand;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 14.08.16.
 */

@TelegramCommand
public class SendMeCommand extends AbstractTelegramCommand {
    @Override
    public String getCommandName() {
        return "/sendme";
    }

    @Override
    public String execute(Message message) {
        return message.getFrom().getFirstName() + ", иди на хуй";
    }

    @Override
    public String getDescription() {
        return "/sendme\nПосылает на хуй";
    }
}
