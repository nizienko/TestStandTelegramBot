package com.github.nizienko.testStandTelegramBot.telegram.hostStorage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by def on 14.08.16.
 */
public class BusyHost implements Serializable {
    private String hostName;
    private Integer userId;
    private String userName;
    private Date busyUntil;
    private Long chatId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBusyUntil() {
        return busyUntil;
    }

    public void setBusyUntil(Date busyUntil) {
        this.busyUntil = busyUntil;
    }
}
