package ru.mativ.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {
    private static final String DEFAULT_PROPERTY_NAME = "application.config.file";
    private static final String DEFAULT_FILE_NAME = "../conf/application-config.xml";

    private Properties properties;
    private String propertyName;

    public ApplicationConfig() {
        this(DEFAULT_PROPERTY_NAME);
    }

    public ApplicationConfig(String propertyName) {
        this.propertyName = propertyName;
        init();
    }

    private void init() {
        properties = new Properties();
        String fullPath = getFullPath();

        try (FileInputStream stream = new FileInputStream(fullPath)) {
            properties.loadFromXML(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFullPath() {
        String path;

        // form gloabal operation system environment
        path = System.getenv(propertyName);
        if (path != null) {
            return path;
        }

        // form start application keys
        path = System.getProperty(propertyName);
        if (path != null) {
            return path;
        }

        // default file name
        return DEFAULT_FILE_NAME;
    }

    public String getPropertyString(String key, String def) {
        return properties.getProperty(key, def);
    }

}
