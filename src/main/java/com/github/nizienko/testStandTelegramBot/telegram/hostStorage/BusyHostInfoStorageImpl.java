package com.github.nizienko.testStandTelegramBot.telegram.hostStorage;

import com.github.nizienko.testStandTelegramBot.telegram.MessageSender;
import com.github.nizienko.testStandTelegramBot.telegram.utils.FileWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by def on 14.08.16.
 */

@Component
@EnableScheduling
public class BusyHostInfoStorageImpl implements BusyHostInfoStorage {
    private BusyHostMap busyHosts;
    private boolean isUpdated = false;
    private FileWorker<BusyHostMap> busyHostsFile = new FileWorker<>(BusyHostMap.class, "busyHostsFile.json");

    @Autowired
    private MessageSender messageSender;

    @PostConstruct
    private void init(){
        busyHosts = busyHostsFile.load();
        if (busyHosts == null) {
            busyHosts = new BusyHostMap();
            busyHostsFile.save(busyHosts);
        }
    }

    public synchronized String addBusyHost(BusyHost newBusyHost) {
        if (busyHosts.get(newBusyHost.getHostName()) != null) {
            BusyHost host = busyHosts.get(newBusyHost.getHostName());
            return String.format("Хост %s уже занят %s до %s",
                    host.getHostName(),
                    host.getUserName(),
                    host.getBusyUntil());
        }
        busyHosts.put(newBusyHost.getHostName(), newBusyHost);
        isUpdated = true;
        return String.format("Хост %s занят %s до %s",
                newBusyHost.getHostName(),
                newBusyHost.getUserName(),
                newBusyHost.getBusyUntil());
    }

    public synchronized String freeBusyHost(BusyHost oldBusyHost) {
        if (busyHosts.get(oldBusyHost.getHostName()) == null) {
            return String.format("Хост %s итак свободен",
                    oldBusyHost.getHostName());
        }
        BusyHost host = busyHosts.get(oldBusyHost.getHostName());
        if (!host.getUserId().equals(oldBusyHost.getUserId())) {
            return String.format("Хост %s может освободить только %s.",
                    oldBusyHost.getHostName(),
                    oldBusyHost.getUserName());
        }
        busyHosts.remove(oldBusyHost.getHostName());
        isUpdated = true;
        return String.format("Хост %s теперь освобожден.\nСпасибо, %s",
                oldBusyHost.getHostName(),
                oldBusyHost.getUserName());
    }

    public synchronized String updateBusyHost(BusyHost oldBusyHost) {
        if (busyHosts.get(oldBusyHost.getHostName()) == null) {
            return String.format("Хост %s был свободен, теперь занят для %s до %s",
                    oldBusyHost.getHostName(),
                    oldBusyHost.getUserName(),
                    oldBusyHost.getBusyUntil());
        }
        BusyHost host = busyHosts.get(oldBusyHost.getHostName());
        if (!host.getUserId().equals(oldBusyHost.getUserId())) {
            return String.format("Хост %s занят %s.",
                    oldBusyHost.getHostName(),
                    oldBusyHost.getUserName());
        }
        busyHosts.remove(oldBusyHost.getHostName());
        busyHosts.put(oldBusyHost.getHostName(), oldBusyHost);
        isUpdated = true;
        return String.format("Хост %s теперь занят до %s.",
                oldBusyHost.getHostName(),
                oldBusyHost.getBusyUntil());
    }

    @Override
    public String hostList() {
        StringBuffer out = new StringBuffer();
        busyHosts.entrySet().forEach(
                (e) -> out.append(String.format("%s %s %s\n",
                        e.getValue().getHostName(),
                        e.getValue().getBusyUntil(),
                        e.getValue().getUserName()))
        );
        return out.toString();
    }

    @Scheduled(fixedDelay = 10000)
    private void saveConfig(){
        if (isUpdated) {
            busyHostsFile.save(busyHosts);
            isUpdated = false;
        }
    }

    @Scheduled(fixedDelay = 60000)
    private void freeHosts(){
        Date now = new Date();
        Set<String> hostsToRemoved = new HashSet<>();
        busyHosts.entrySet().forEach(
                (e) -> {
                    if (e.getValue()
                        .getBusyUntil()
                        .before(now)){
                        hostsToRemoved.add(e.getKey());
                    }
                }
        );
        if (hostsToRemoved.size() > 0) {
            hostsToRemoved.forEach((s) -> {
                BusyHost removedHost = busyHosts.get(s);
                busyHosts.remove(s);
                messageSender.sendMessage(removedHost.getChatId(),
                        String.format("Хост %s занятый %s освобожден.",
                                removedHost.getHostName(),
                                removedHost.getUserName()));
            });
            isUpdated = true;
        }
    }
}
