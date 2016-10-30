package com.github.nizienko.telegramBot.telegram.commands.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by def on 14.08.16.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TelegramCommand {
    PermissionLevel permissionLevel() default PermissionLevel.USER;
}
