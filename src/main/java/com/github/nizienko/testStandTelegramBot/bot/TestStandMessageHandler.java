package com.github.nizienko.testStandTelegramBot.bot;

import com.github.nizienko.testStandTelegramBot.bot.command.BadCommandSyntaxException;
import com.github.nizienko.testStandTelegramBot.bot.command.Command;
import com.github.nizienko.testStandTelegramBot.bot.command.CommandFactory;
import com.github.nizienko.testStandTelegramBot.bot.command.UnknownCommandException;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */
public class TestStandMessageHandler implements MessageHandler {
    public String process(Message message) {
        try {
            Command command = CommandFactory.getCommand(message.getText());
            return command.execute(message.getText().split(" "));
        }
        catch (UnknownCommandException e) {
            return "Привет,  " + message.getFrom().getFirstName() + "\nНабери 'help'";
        }
        catch (BadCommandSyntaxException e) {
            return String.format("Что-то не так\n%s", e.getMessage());
        }
    }
}
