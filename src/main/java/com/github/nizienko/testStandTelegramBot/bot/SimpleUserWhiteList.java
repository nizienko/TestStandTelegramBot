package com.github.nizienko.testStandTelegramBot.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by def on 13.08.16.
 */
public class SimpleUserWhiteList implements UsersWhiteList {
    private static List<Integer> whiteList = new ArrayList<Integer>();
    private static Integer[] ids = {
            164009017,
            209106005,
            164979103
    };

    public SimpleUserWhiteList(){
        whiteList.addAll(Arrays.asList(ids));
    }
    public boolean isAllowed(Integer userId) {
        return whiteList.contains(userId);
    }
}
