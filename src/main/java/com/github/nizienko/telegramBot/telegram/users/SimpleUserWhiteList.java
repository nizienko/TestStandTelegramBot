package com.github.nizienko.telegramBot.telegram.users;

import com.github.nizienko.telegramBot.telegram.commands.core.PermissionLevel;
import com.github.nizienko.telegramBot.telegram.utils.FileWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by def on 13.08.16.
 */

@Service
@EnableScheduling
public class SimpleUserWhiteList implements UserWhiteList {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserWhiteList.class);
    private boolean isSaveNeeded = false;
    private final FileWorker<Set> whiteListFile = new FileWorker<Set>(Set.class, "whiteList.json");
    private Set whiteList;

    @Value("${telegram.testStandBot.admin.id}")
    private Integer adminId;

    @PostConstruct
    private void loadUsers(){
        whiteList = whiteListFile.load();
        if (whiteList == null) {
            whiteList = new HashSet();
        }
    }

    private boolean isUser(Integer userId) {
        return whiteList.contains(userId) || isAdmin(userId);
    }

    private boolean isAdmin(Integer userId) {
        return userId.equals(adminId);
    }

    @Override
    public boolean isAllowed(Integer userId, PermissionLevel role) {
        switch (role) {
            case ANONYMOUS: return true;
            case USER: return isUser(userId);
            case ADMIN: return isAdmin(userId);
        }
        return false;
    }

    @Override
    public void addUser(Integer userId) {
        if (whiteList.contains(userId)) {
            throw new IllegalStateException("Такой юзер уже есть в списке");
        }
        LOG.info(String.format("Добавляем юзера %d", userId));
        whiteList.add(userId);
        isSaveNeeded = true;
    }

    @Override
    public void delUser(Integer userId) {
        LOG.info(String.format("Удаляем юзера %d", userId));
        if (!whiteList.contains(userId)) {
            throw new IllegalStateException("Такого юзера итак нет в списке");
        }
        whiteList.remove(userId);
        isSaveNeeded = true;
    }

    @Scheduled(fixedDelay = 10000)
    private void saveFile(){
        if (isSaveNeeded) {
            whiteListFile.save(whiteList);
            isSaveNeeded = false;
        }
    }
}
