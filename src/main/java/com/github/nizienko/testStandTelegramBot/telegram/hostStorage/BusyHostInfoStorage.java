package com.github.nizienko.testStandTelegramBot.telegram.hostStorage;

/**
 * Created by def on 14.08.16.
 */
public interface BusyHostInfoStorage {
    String addBusyHost(BusyHost busyHost);
    String freeBusyHost(BusyHost busyHost);
    String updateBusyHost(BusyHost busyHost);
    String hostList();

}
