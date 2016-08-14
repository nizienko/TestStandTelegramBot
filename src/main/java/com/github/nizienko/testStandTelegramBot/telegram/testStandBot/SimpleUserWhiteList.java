package com.github.nizienko.testStandTelegramBot.telegram.testStandBot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by def on 13.08.16.
 */

@Service
public class SimpleUserWhiteList implements UsersWhiteList {
    private List<Integer> whiteList = new ArrayList<Integer>();
    private Integer[] ids = {
            164009017,
            209106005,
            164979103,
            57602516
    };

    public SimpleUserWhiteList(){
        whiteList.addAll(Arrays.asList(ids));
    }
    public boolean isAllowed(Integer userId) {
        return whiteList.contains(userId);
    }
}
