package ru.mativ.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Load prorerties from file.<br>
 * Priority:<br>
 * 1 - get file path from system environment by parametr name = "application.config.file"<br>
 * 2 - get path from application keys "-Dapplication.config.file="<br>
 * 3 - from classpath by file name "application-config.xml"<br>
 * If properties file is not exist, then all properties will be get from default fields.<br>
 * <br>
 * You may change propertie name in constructor.<br>
 */
public class ApplicationConfigLoader {
    private static final String DEFAULT_PROPERTY_NAME = "application.config.file";
    private static final String DEFAULT_FILE_NAME = "application-config.xml";

    private Properties properties;
    private String propertyName;

    /**
     * Search property with name "application.config.file" in OS environment, application keys and classpath.<br>
     * And load configurations from file with path from this properties.<br>
     * By default file name is "application-config.xml"
     */
    public ApplicationConfigLoader() {
        this(DEFAULT_PROPERTY_NAME);
    }

    /**
     * Search property with name <b>propertyName</b> in OS environment, application keys and classpath.<br>
     * And load configurations from file with path from this properties.<br>
     * By default file name is "application-config.xml"
     */
    public ApplicationConfigLoader(String propertyName) {
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
        return this.getClass().getClassLoader().getResource(DEFAULT_FILE_NAME).getFile();
    }

    /**
     * Return String value of property with name <b>key</b>.<br>
     * If this property missing, then return <b>def</b> value.
     */
    public String getPropertyString(String key, String def) {
        return properties.getProperty(key, def);
    }

}
