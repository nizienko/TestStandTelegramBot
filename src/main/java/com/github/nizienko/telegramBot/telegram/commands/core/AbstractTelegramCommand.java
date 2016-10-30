package com.github.nizienko.telegramBot.telegram.commands.core;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by def on 14.08.16.
 */
public abstract class AbstractTelegramCommand implements Command {

    @Autowired
    private TelegramCommandStorage storage;

    @PostConstruct
    public void init() {
        storage.put(this);
    }

    public PermissionLevel getRole(){
        return this.getClass().getAnnotation(TelegramCommand.class).permissionLevel();
    }
}
