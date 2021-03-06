package com.github.nizienko.telegramBot.telegram.commands.core;

import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */
public interface Command {
    String getCommandName();
    String execute(Message message);
    String getDescription();
    PermissionLevel getRole();
}
