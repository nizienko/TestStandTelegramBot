package com.github.nizienko.testStandTelegramBot.bot;


/**
 * Created by def on 13.08.16.
 */
public interface UsersWhiteList {
    boolean isAllowed(String userName);
}
