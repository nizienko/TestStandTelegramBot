package com.github.nizienko.testStandTelegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by def on 13.08.16.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(
                new Class<?>[] {AppConfiguration.class, Application.class} , args);
    }
}
