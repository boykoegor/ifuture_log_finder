package com.boyko.ifuture.log_finder;

import com.boyko.ifuture.log_finder.gui.MainWindow;
import org.apache.log4j.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("I started application");
        MainWindow app = new MainWindow();
        app.setVisible(true);
    }
}