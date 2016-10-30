package com.github.nizienko.telegramBot;

import com.github.nizienko.telegramBot.telegram.commands.core.TelegramCommand;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by def on 25.10.16.
 */
@Configuration
@ComponentScan(basePackages = "com.github.nizienko.telegramBot.telegram.commands",
        includeFilters = @ComponentScan.Filter({TelegramCommand.class}))
public class AppConfiguration {

}
