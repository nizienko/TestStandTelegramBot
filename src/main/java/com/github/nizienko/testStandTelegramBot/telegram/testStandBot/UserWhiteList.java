package com.github.nizienko.testStandTelegramBot.telegram.testStandBot;


import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.PermissionLevel;

/**
 * Created by def on 13.08.16.
 */
public interface UserWhiteList {
    boolean isAllowed(Integer userId, PermissionLevel role);
    void addUser(Integer userId);
    void delUser(Integer userId);
}
