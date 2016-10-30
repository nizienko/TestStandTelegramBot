package com.github.nizienko.telegramBot.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by def on 25.10.16.
 */

@Component
@EnableScheduling
public class TelegramImpl implements Telegram {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramImpl.class);

    private RegisteredTelegramLongPollingBot bot;
    private List<Receiver> receiverList = new ArrayList<>();

    private Queue<SendMessage> queue = new PriorityQueue<>();

    @PostConstruct
    private void init() {
        bot = new RegisteredTelegramLongPollingBot(this, botName, botToken);
    }

    @Value("${telegram.testStandBot.name}")
    private String botName;

    @Value("${telegram.testStandBot.token}")
    private String botToken;

    @Override
    public void sendTextMessage(Long id, String message) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(String.valueOf(id));
        sendMessageRequest.setText(message);
        queue.add(sendMessageRequest);
    }

    @Override
    public void subscribeOnMessages(Receiver receiver) {
        receiverList.add(receiver);
    }

    @Override
    public void receive(Update update) {
        receiverList.forEach((r)-> r.processMessage(update));
    }

    @Scheduled(fixedDelay = 1000)
    public void process() {
        if (!queue.isEmpty()) {
            LOG.info(String.format("Нужено отправить %d сообещний", queue.size()));
            while (!queue.isEmpty()) {
                SendMessage sendMessage = queue.poll();
                bot.send(sendMessage);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
