package ru.mativ.tools;

public class AppConfig {
    private static final String PROPERTY_NAME = "laborcoast.config.file";
    private static ApplicationConfig config = new ApplicationConfig(PROPERTY_NAME);

    public static String applicationGreetingMessage() {
        return config.getPropertyString("application.greeting.message", "");
    }

    public static String myBatisConfigPath() {
        return config.getPropertyString("mybatis.config.path", "");
    }

}
