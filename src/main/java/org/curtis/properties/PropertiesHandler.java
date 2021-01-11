package org.curtis.properties;

import org.curtis.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesHandler {
    private static Map<String, Properties> propertiesFiles = new HashMap<>();
    private static String prefix = null;
    private static String PROPERTIES_DIRECTORY = System.getProperty("user.home") + "/.musicxml";
    private static String LOCAL_PROPERTIES_FILE = "musicxml.properties";
    public static String LOCAL_PROPERTIES_FILENAME = PROPERTIES_DIRECTORY + "/" + LOCAL_PROPERTIES_FILE;
    private static final String LOCAL_PROPERTIES_NAME = "local properties";
    private static final String DATABASE_PROPERTIES_NAME = "database properties";
    protected static String KEY_FILENAME = PROPERTIES_DIRECTORY + "/.key";

    public static void addLocalProperties() {
        try {
            File localPropertiesFile = new File(LOCAL_PROPERTIES_FILENAME);
            if (!localPropertiesFile.exists()) return;

            addProperties(LOCAL_PROPERTIES_NAME, new FileInputStream(LOCAL_PROPERTIES_FILENAME));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addDatabaseProperties() {
        if (propertiesFiles.get(DATABASE_PROPERTIES_NAME) != null) return;

        try {
            InputStream inputStream = PropertiesHandler.class.getClassLoader().getResourceAsStream("properties/database.properties");
            addProperties(DATABASE_PROPERTIES_NAME, inputStream);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProperties(String propertiesName, InputStream inputStream) throws PropertyFileNotFoundException {
        try {
            Properties properties = EncryptionHandler.getInstance().getProperties(inputStream);
            propertiesFiles.put(propertiesName, properties);
        } catch (IOException e) {
            throw new PropertyFileNotFoundException("Add Properties: " + propertiesName + " not added");
        }
    }

    public static void setPrefix(String prefix) {
        PropertiesHandler.prefix = prefix;
    }

    public static String getString(String propertyName) throws PropertyException {
        if (StringUtil.isEmpty(propertyName)) {
            throw new PropertyException("Property name is empty");
        }

        String propertyValue;

        for (Properties propertiesFile : propertiesFiles.values()) {
            // find property with prefix first
            propertyValue = propertiesFile.getProperty(prefix + "." + propertyName);
            if (propertyValue != null) return propertyValue;
        }

        for (Properties propertiesFile : propertiesFiles.values()) {
            propertyValue = propertiesFile.getProperty(propertyName);
            if (propertyValue != null) return propertyValue;
        }

        throw new PropertyException("Property not found: " + propertyName);
    }

    public static String getRequiredProperty(String propertyName) {
        String propertyValue;
        try {
            propertyValue = getString(propertyName);
        } catch (PropertyException e) {
            throw new RequiredPropertyNotFoundException(e.getMessage());
        }
        return propertyValue;
    }

    public static String getOptionalProperty(String propertyName) {
        String propertyValue;
        try {
            propertyValue = getString(propertyName);
        } catch (PropertyException e) {
            propertyValue = "";
        }
        return propertyValue;
    }

    public static boolean getBoolean(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);

        return propertyValue.equals("1") || Boolean.parseBoolean(propertyValue);
    }

    public static boolean isEncryptedProperty(String propertyName) {
        if (StringUtil.isEmpty(propertyName)) return false;

        return propertyName.startsWith("ENC(");
    }
}
