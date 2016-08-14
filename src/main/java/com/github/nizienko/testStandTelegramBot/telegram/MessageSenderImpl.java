package com.github.nizienko.testStandTelegramBot.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.AbstractQueue;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by def on 15.08.16.
 */

@Component
@EnableScheduling
public class MessageSenderImpl implements MessageSender {
    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderImpl.class);
    private Queue<MessageToSend> queue = new PriorityQueue<MessageToSend>() {
    };

    private static class MessageToSend {
        private Long id;
        private String text;

        MessageToSend(Long id, String text) {
            this.id = id;
            this.text = text;
        }

        Long getId() {
            return id;
        }

        String getText() {
            return text;
        }
    }

    @Autowired
    private TelegramBot telegramBot;

    @Override
    public void sendMessage(Long chatId, String text) {
        queue.add(new MessageToSend(chatId, text));
        LOG.info(String.format("Сообщение '%s' поставлено в очередь для отправки", text));
    }

    @Scheduled(fixedDelay = 10000)
    public void process() {
        if (!queue.isEmpty()) {
            LOG.info(String.format("Нужено отправить %d сообещний", queue.size()));
            while (!queue.isEmpty()) {
                MessageToSend messageToSend = queue.poll();
                telegramBot.sendMessage(messageToSend.getId(), messageToSend.getText());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
