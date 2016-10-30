package com.github.nizienko.telegramBot.telegram.commands;

import com.github.nizienko.telegramBot.telegram.commands.core.AbstractTelegramCommand;
import com.github.nizienko.telegramBot.telegram.commands.core.BadCommandSyntaxException;
import com.github.nizienko.telegramBot.telegram.commands.core.TelegramCommand;
import com.github.nizienko.telegramBot.telegram.console.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 26.10.16.
 */

@TelegramCommand
public class ConsoleCommand  extends AbstractTelegramCommand {
    @Override
    public String getCommandName() {
        return "/run";
    }

    @Autowired
    private ConsoleService consoleService;

    @Override
    public String execute(Message message) {
        final String cmd = message.getText().replaceAll("(/run )(.*)", "$2");
        if (cmd.startsWith("/run")) {
            throw new BadCommandSyntaxException(this);
        }
        return consoleService.run(cmd);
    }

    @Override
    public String getDescription() {
        return "run cmd command";
    }
}
