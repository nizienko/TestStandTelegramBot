package com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.list;

import com.github.nizienko.testStandTelegramBot.telegram.hostStorage.BusyHost;
import com.github.nizienko.testStandTelegramBot.telegram.hostStorage.BusyHostInfoStorage;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.AbstractTelegramCommand;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.BadCommandSyntaxException;
import com.github.nizienko.testStandTelegramBot.telegram.testStandBot.command.TelegramCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.objects.Message;

import java.util.*;

/**
 * Created by def on 14.08.16.
 */

@TelegramCommand
public class HostCommand extends AbstractTelegramCommand {

    @Autowired
    private BusyHostInfoStorage busyHostInfoStorage;

    @Override
    public String getCommandName() {
        return "/host";
    }

    @Override
    public String execute(Message message) {
        try {
            String[] args = message.getText().replaceAll("[\\s]{2,}", " ").trim().split(" ");
            Action action = Action.parse(args[1]);
            switch (action) {
                case LIST: return busyHostInfoStorage.hostList();
                case BUSY_HOST: {
                    BusyHost host = new BusyHost();
                    host.setHostName(args[2]);
                    host.setChatId(message.getChatId());
                    host.setUserId(message.getFrom().getId());
                    host.setUserName(String.format("%s %s(%s)",
                            message.getFrom().getFirstName(),
                            message.getFrom().getLastName(),
                            message.getFrom().getUserName())
                    );
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(new Date());
                    calendar.add(getValue(args[5]), Integer.parseInt(args[4]));
                    host.setBusyUntil(calendar.getTime());
                    return busyHostInfoStorage.addBusyHost(host);
                }
                case FREE_HOST: {
                    BusyHost host = new BusyHost();
                    host.setHostName(args[2]);
                    host.setUserId(message.getFrom().getId());
                    host.setUserName(String.format("%s %s(%s)",
                            message.getFrom().getFirstName(),
                            message.getFrom().getLastName(),
                            message.getFrom().getUserName())
                    );
                    return busyHostInfoStorage.freeBusyHost(host);
                }
            }
            return "готово";
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new BadCommandSyntaxException(this);
        }
    }
    private final static String[] minute = {"минут", "минуты", "минуту"};
    private final static String[] hour = {"час", "часа", "часов"};
    private final static String[] day = {"день", "дня", "дней"};

    private int getValue(String arg) {
        arg = arg.trim().toLowerCase();
        if (Arrays.asList(minute).contains(arg)) {
            return Calendar.MINUTE;
        }
        if (Arrays.asList(hour).contains(arg)) {
            return Calendar.HOUR;
        }
        if (Arrays.asList(day).contains(arg)) {
            return Calendar.DAY_OF_YEAR;
        }
        throw new IllegalArgumentException();
    }

    private enum Action {
        BUSY_HOST, FREE_HOST, LIST;

        private static Set<String> busyWords;
        private static Set<String> freeWords;

        public static Action parse(String arg) {
            if (busyWords == null){
                busyWords = new HashSet<>();
                freeWords = new HashSet<>();
                Collections.addAll(busyWords, "занимаю", "занят");
                Collections.addAll(freeWords, "освобождаю", "свободен");
            }

            if (busyWords.contains(arg.trim().toLowerCase())) {
                return BUSY_HOST;
            }
            else if (freeWords.contains(arg.trim().toLowerCase())){
                return FREE_HOST;
            }
            else if (arg.trim().toLowerCase().equals("list")) {
                return LIST;
            }
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getDescription() {
        return "/host занимаю isla на 5 дней\n" +
                "/host занимаю boulogne на 3 часа\n" +
                "/host освобождаю boulogne\n";
    }
}
