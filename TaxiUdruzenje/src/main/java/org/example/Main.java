package org.example;

import org.example.ui_handler.MainUIHandler;
import org.slf4j.simple.SimpleLogger;

public class Main {
    public static void main(String[] args) {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");

        MainUIHandler mainUIHandler = new MainUIHandler();
        try {
            mainUIHandler.handleMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}