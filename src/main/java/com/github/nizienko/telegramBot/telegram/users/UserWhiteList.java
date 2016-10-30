package com.github.nizienko.telegramBot.telegram.users;


import com.github.nizienko.telegramBot.telegram.commands.core.PermissionLevel;

/**
 * Created by def on 13.08.16.
 */
public interface UserWhiteList {
    boolean isAllowed(Integer userId, PermissionLevel role);
    void addUser(Integer userId);
    void delUser(Integer userId);
}
