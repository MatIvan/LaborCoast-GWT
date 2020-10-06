package ru.mativ.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ru.mativ.tools.AppConfig;

public class ApplicationInitializer implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("#############");
        System.out.println("#   START   #");
        System.out.println("#############");
        System.out.println("GREET: " + AppConfig.applicationGreetingMessage());
        System.out.println("");
    }

    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("#############");
        System.out.println("#   STOP    #");
        System.out.println("#############");
        System.out.println("");
    }
}
