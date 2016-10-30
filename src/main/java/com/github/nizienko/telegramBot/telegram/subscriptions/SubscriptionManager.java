package com.github.nizienko.telegramBot.telegram.subscriptions;

import java.util.List;

/**
 * Created by def on 25.10.16.
 */
public interface SubscriptionManager {
    void addSubscription(Subscription subscription);
    void dellSubscription(Subscription subscription);
    List<Long>getSubscribersByType(SubscriptionType type);
}
