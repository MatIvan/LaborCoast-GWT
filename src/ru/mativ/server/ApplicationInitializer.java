package ru.mativ.server;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ru.mativ.tools.AppConf;
import ru.mativ.tools.ApplicationConfigLoader;

public class ApplicationInitializer implements ServletContextListener {
    private static final Logger Log = Logger.getLogger(ApplicationInitializer.class.getName());

    public void contextInitialized(ServletContextEvent event) {
        AppConf.init(new ApplicationConfigLoader());

        Log.info("#############");
        Log.info("#   START   #");
        Log.info("#############");
        Log.info(AppConf.applicationGreetingMessage());
        Log.info("");
        Log.finest("FINEST");
        Log.finer("FINER");
        Log.fine("FINE");
        Log.config("CONFIG");
        Log.info("INFO");
        Log.warning("WARNING");
        Log.severe("SEVERE");
        Log.info("");
    }

    public void contextDestroyed(ServletContextEvent event) {
        Log.info("");
        Log.info("#############");
        Log.info("#   STOP    #");
        Log.info("#############");
    }
}
