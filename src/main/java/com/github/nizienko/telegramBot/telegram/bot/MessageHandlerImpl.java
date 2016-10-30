package com.github.nizienko.telegramBot.telegram.bot;

import com.github.nizienko.telegramBot.telegram.commands.core.BadCommandSyntaxException;
import com.github.nizienko.telegramBot.telegram.commands.core.Command;
import com.github.nizienko.telegramBot.telegram.commands.core.TelegramCommandStorage;
import com.github.nizienko.telegramBot.telegram.commands.core.UnknownCommandException;
import com.github.nizienko.telegramBot.telegram.users.UserWhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import javax.annotation.PostConstruct;

/**
 * Created by def on 25.10.16.
 */

@Service
public class MessageHandlerImpl implements MessageHandler, Receiver {
    private static final Logger LOG = LoggerFactory.getLogger(MessageHandlerImpl.class);

    @Autowired
    private Telegram telegram;

    @Autowired
    private UserWhiteList whiteList;

    @Autowired
    private TelegramCommandStorage commandStorage;

    @PostConstruct
    private void init(){
        telegram.subscribeOnMessages(this);
    }

    @Override
    public void processMessage(Update update) {
        LOG.info(update.toString());
        if(update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                Integer fromId = message.getFrom().getId();
                Long chatId = message.getChatId();
                try {
                    Command command = commandStorage.get(message.getText());
                    if (whiteList.isAllowed(fromId, command.getRole())) {
                        final String answer = command.execute(message);
                        if (answer.length() > 0) {
                            telegram.sendTextMessage(chatId, answer);
                        }
                    }
                    else {
                        telegram.sendTextMessage(chatId, String.format("Нет доступа для %s(%d)",
                                message.getFrom().getFirstName(), message.getFrom().getId()));
                    }
                }
                catch (UnknownCommandException e) {
                    telegram.sendTextMessage(chatId,
                            "Привет,  " + message.getFrom().getFirstName() + "\nНабери '/help'");
                }
                catch (BadCommandSyntaxException e) {
                    telegram.sendTextMessage(chatId,
                            String.format("Что-то не так. Описание команды:\n%s", e.getMessage()));
                }
            }
        }
    }
}
