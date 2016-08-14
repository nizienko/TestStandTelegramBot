package com.github.nizienko.testStandTelegramBot.telegram;

import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.TestStandBot;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.ITelegramLongPollingBot;

import javax.annotation.PostConstruct;

/**
 * Created by def on 14.08.16.
 */

@Component
public class TelegramApiServiceImpl implements TelegramApiService {

    @Autowired
    @Qualifier("testStandBot")
    private TelegramBot testStandBot;

    @PostConstruct
    public void startTelegramBotsApi(){
        final TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(testStandBot.getLongPollingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
