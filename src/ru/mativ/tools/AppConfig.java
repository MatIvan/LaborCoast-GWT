package ru.mativ.tools;

/**
 * Properties (configurations) for LaborCoast project.
 */
public class AppConfig {
    private static final String DEFAULT_DB_CONFIG_FILE_NAME = "MyBatis-config.xml";

    private static final String PROPERTY_NAME = "laborcoast.config.file";
    private static ApplicationConfigLoader config = new ApplicationConfigLoader(PROPERTY_NAME);

    public static String applicationGreetingMessage() {
        return config.getPropertyString("application.greeting.message", "");
    }

    public static String myBatisConfigPath() {
        String defaultPath = AppConfig.class.getClassLoader().getResource(DEFAULT_DB_CONFIG_FILE_NAME).getFile();
        return config.getPropertyString("mybatis.config.path", defaultPath);
    }

}
