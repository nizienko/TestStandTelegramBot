package com.github.nizienko.testStandTelegramBot.telegram.testStandBot;

import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.BadCommandSyntaxException;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.Command;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.TelegramCommandStorage;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.UnknownCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by def on 13.08.16.
 */

@Service
public class TestStandMessageHandler implements MessageHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TestStandMessageHandler.class);

    @Autowired
    private TelegramCommandStorage commandStorage;

    public String process(Message message) {
        try {
            Command command = commandStorage.get(message.getText());
            return command.execute(message);
        }
        catch (UnknownCommandException e) {
            return "Привет,  " + message.getFrom().getFirstName() + "\nНабери '/help'";
        }
        catch (BadCommandSyntaxException e) {
            return String.format("Что-то не так\n%s", e.getMessage());
        }
    }
}
