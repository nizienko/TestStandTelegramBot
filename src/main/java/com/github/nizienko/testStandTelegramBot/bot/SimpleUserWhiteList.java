package com.github.nizienko.testStandTelegramBot.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by def on 13.08.16.
 */
public class SimpleUserWhiteList implements UsersWhiteList {
    private static List<Integer> whiteList = new ArrayList<Integer>();

    public SimpleUserWhiteList(){
        whiteList.add(164009017);
        whiteList.add(209106005);
    }
    public boolean isAllowed(Integer userId) {
        return whiteList.contains(userId);
    }
}
