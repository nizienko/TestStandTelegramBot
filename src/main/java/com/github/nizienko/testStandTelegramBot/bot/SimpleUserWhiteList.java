package com.github.nizienko.testStandTelegramBot.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by def on 13.08.16.
 */
public class SimpleUserWhiteList implements UsersWhiteList {
    private static List<String> whiteList = new ArrayList<String>();

    public SimpleUserWhiteList(){
        whiteList.add("eugene_nizienko");
    }
    public boolean isAllowed(String userName) {
        return whiteList.contains(userName);
    }
}
