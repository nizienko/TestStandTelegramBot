package com.github.nizienko.testStandTelegramBot.bot.command;

/**
 * Created by def on 13.08.16.
 */
public interface Command {
    String execute(String[] args);
    String getDescription();
}
