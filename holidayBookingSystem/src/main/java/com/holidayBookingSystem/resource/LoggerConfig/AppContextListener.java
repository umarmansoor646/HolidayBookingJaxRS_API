package com.holidayBookingSystem.resource.LoggerConfig;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(AppContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            LogManager.getLogManager().readConfiguration(AppContextListener.class.getResourceAsStream("/logging.properties"));
            LOGGER.info("Logging configuration loaded successfully");
            
        } catch (IOException e) {
            LOGGER.severe("Could not load logging configuration: " + e.getMessage());
        }
    }
}
