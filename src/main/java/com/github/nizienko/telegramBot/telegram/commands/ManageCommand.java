package com.github.nizienko.telegramBot.telegram.commands;

import com.github.nizienko.telegramBot.telegram.users.UserWhiteList;
import com.github.nizienko.telegramBot.telegram.commands.core.AbstractTelegramCommand;
import com.github.nizienko.telegramBot.telegram.commands.core.BadCommandSyntaxException;
import com.github.nizienko.telegramBot.telegram.commands.core.TelegramCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Message;

import static com.github.nizienko.telegramBot.telegram.commands.core.PermissionLevel.*;

/**
 * Created by def on 14.08.16.
 */

@TelegramCommand(permissionLevel = ADMIN)
public class ManageCommand extends AbstractTelegramCommand {

    @Autowired
    private UserWhiteList whiteList;

    @Override
    public String getCommandName() {
        return "/manage";
    }

    @Override
    public String execute(Message message) {
        final String[] args = message.getText().replaceAll("[\\s]{2,}", " ").split(" ");
        try {
            final Action action = Action.valueOf(args[1].toUpperCase());
            switch (action) {
                case ADD_USER: {
                    final Integer userId = Integer.parseInt(args[2]);
                    whiteList.addUser(userId);
                    break;
                }
                case DEL_USER: {
                    final Integer userId = Integer.parseInt(args[2]);
                    whiteList.delUser(userId);
                    break;
                }
            }
            return "готово";
        }
        catch (IllegalStateException e) {
            return e.getMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new BadCommandSyntaxException(this);
        }
    }

    private enum Action {
        ADD_USER,
        DEL_USER
    }

    @Override
    public String getDescription() {
        return "/manage add_user 57602516\n" +
                "/manage del_user 57602516";
    }
}
