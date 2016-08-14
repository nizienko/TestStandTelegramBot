package com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command;

import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.TestStandMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by def on 13.08.16.
 */

@Component
public class TelegramCommandStorageImpl implements TelegramCommandStorage{
    private Map<String, Command> storage = new HashMap<String, Command>();
    private static final Logger LOG = LoggerFactory.getLogger(TelegramCommandStorageImpl.class);

    @Override
    public void put(Command command) {
        LOG.info(String.format("Registered command '%s'", command.getCommandName()));
        storage.put(command.getCommandName().toLowerCase(), command);
    }

    @Override
    public Command get(String message) {
        try {
            String commandName = message.split(" ")[0].trim().toLowerCase();
            if (commandName.contains("@")) {
                commandName = commandName.split("@")[0];
            }
            Command command = storage.get(commandName);
            if (command != null){
                return command;
            }
        }
        catch (Exception e) {
            throw new UnknownCommandException();
        }
        throw new UnknownCommandException();
    }
}
