package com.github.nizienko.telegramBot.telegram.console;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by def on 25.10.16.
 */

@Component
public class ConsoleServiceImpl implements ConsoleService {


    @Override
    public String run(String cmd) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            if (p.exitValue() == 0) {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));

                String line = "";

                while ((line = reader.readLine())!= null) {
                    output.append(line + "\n");
                }
            }
            else {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(p.getErrorStream()));

                String line = "";

                while ((line = reader.readLine())!= null) {
                    output.append(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
