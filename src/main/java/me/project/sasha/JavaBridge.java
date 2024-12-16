package me.project.sasha;

import java.util.Arrays;

public class JavaBridge {

    private final Service service = Service.getInstance();

    public void print(String message) {
        String[] words = message.split(" ");
        String command = words[0];
        switch (command) {
            case "ADD" -> service.add(words);
            case "READY" -> service.getTasks();
            case "CLOSE" -> service.close();
        }
    }
}

