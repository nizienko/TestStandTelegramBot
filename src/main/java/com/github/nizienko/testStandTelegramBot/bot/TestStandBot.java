package com.github.nizienko.testStandTelegramBot.bot;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by def on 13.08.16.
 */
public class TestStandBot extends TelegramLongPollingBot {
    private final static String BOT_NAME = "YamoneyTestStandBot";
    private final static String BOT_TOKEN = "xxx";
    private final MessageHandler messageHandler = new TestStandMessageHandler();
    private final UsersWhiteList whiteList = new SimpleUserWhiteList();

    public void onUpdateReceived(Update update) {
        System.out.println(update);
        if(update.hasMessage()){
            Message message = update.getMessage();

            if(message.hasText()){
                if (whiteList.isAllowed(message.getFrom().getId())) {
                    sendAnswer(message.getChatId(), messageHandler.process(message));
                }
                else {
                    sendAnswer(message.getChatId(), "Нет доступа");
                }
            }
        }
    }

    private void sendAnswer(Long chatId, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId.toString()); //who should get from the message the sender that sent it.
        sendMessageRequest.setText(text);
        try {
            sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
        } catch (TelegramApiException e) {
            //do some error handling
        }
    }

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}
