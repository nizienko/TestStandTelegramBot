package com.github.nizienko.telegramBot.telegram.subscriptions;

/**
 * Created by def on 25.10.16.
 */
public class Subscription {
    private SubscriptionType subscriptionType;
    private Long chatId;

    public Subscription(SubscriptionType subscriptionType, Long chatId) {
        this.subscriptionType = subscriptionType;
        this.chatId = chatId;
    }
}
