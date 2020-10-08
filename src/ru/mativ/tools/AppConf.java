package ru.mativ.tools;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Properties (configurations) for LaborCoast project.
 */
public class AppConf {
    private static ApplicationConfigLoader config;

    public static void init(ApplicationConfigLoader config) {
        AppConf.config = config;
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return config.getSqlSessionFactory();
    }

    public static String applicationGreetingMessage() {
        return config.getPropertyString("application.greeting.message", "Have no greeting message :(");
    }

}
