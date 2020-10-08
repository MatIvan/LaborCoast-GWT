package ru.mativ.tools;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Load prorerties from file "application-config.xml".<br>
 */
public class ApplicationConfigLoader {
    private static final Logger Log = Logger.getLogger("ApplicationConfigLoader");

    private static final String PROPERTIES_FILE = "configure/application.properties";
    private static final String LOGGING_CONFIG_FILE = "configure/logging.properties";
    private static final String DB_CONFIG_FILE = "MyBatis-config.xml";

    private Properties properties;

    public ApplicationConfigLoader() {
        loadLoggingConfigs(LOGGING_CONFIG_FILE);

        properties = new Properties();
        loadPropertiesFromFile(PROPERTIES_FILE);
    }

    private void loadPropertiesFromFile(String filename) {
        try {
            properties.load(getInputStream(filename));
            Log.info("Properties loaded from: " + filename);
        } catch (Exception e) {
            Log.log(Level.SEVERE, "Can't load properties from file: " + filename, e);
        }
    }

    private void loadLoggingConfigs(String filename) {
        try {
            LogManager.getLogManager().readConfiguration(getInputStream(filename));
            Log.info("Logging configs loaded from: " + filename);
        } catch (Exception e) {
            Log.log(Level.SEVERE, "Can't load logging configs from file: " + filename, e);
        }
    }

    public SqlSessionFactory getSqlSessionFactory() {
        String filename = DB_CONFIG_FILE;
        try {
            ClassLoader classLoader = ApplicationConfigLoader.class.getClassLoader();
            InputStream stream = classLoader.getResourceAsStream(filename);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
            Log.info("MyBatis configs loaded from: " + filename);
            return factory;
        } catch (Exception e) {
            Log.log(Level.SEVERE, "Can't load MyBatis configs from file: " + filename, e);
        }
        return null;
    }

    private InputStream getInputStream(String filename) {
        ClassLoader classLoader = ApplicationConfigLoader.class.getClassLoader();
        return classLoader.getResourceAsStream(filename);
    }

    /**
     * Return String value of property with name <b>key</b>.<br>
     * If this property missing, then return <b>def</b> value.
     */
    public String getPropertyString(String key, String def) {
        return properties.getProperty(key, def);
    }

}
