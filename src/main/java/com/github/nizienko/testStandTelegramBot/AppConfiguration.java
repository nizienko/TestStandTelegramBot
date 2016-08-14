package com.github.nizienko.testStandTelegramBot;

import com.github.nizienko.testStandTelegramBot.telegram.TelegramBot;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.SimpleUserWhiteList;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.TestStandBot;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.UserWhiteList;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.TelegramCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by def on 14.08.16.
 */

@Configuration
@ComponentScan(basePackages = "com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.list",
        includeFilters = @ComponentScan.Filter({TelegramCommand.class}))
public class AppConfiguration {

    @Value("${telegram.testStandBot.name}")
    private String botName;

    @Value("${telegram.testStandBot.token}")
    private String botToken;

    @Value("${telegram.testStandBot.admin.id}")
    private Integer adminId;

    @Bean
    public TelegramBot testStandBot(){
        return new TestStandBot(botName, botToken);
    }

    @Bean
    public UserWhiteList userWhiteList(){
        return new SimpleUserWhiteList(adminId);
    }

}
