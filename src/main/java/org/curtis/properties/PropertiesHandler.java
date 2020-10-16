package org.curtis.properties;

import org.curtis.util.StringUtil;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertiesHandler {
    private static Map<String, ResourceBundle> bundles = new HashMap<>();
    private static String prefix = null;
    private static String PROPERTIES_DIRECTORY = System.getProperty("user.home") + "/.musicxml";
    private static String PROPERTIES_BUNDLE = "musicxml";
    public static String PROPERTIES_FILENAME = PROPERTIES_DIRECTORY + "/" + PROPERTIES_BUNDLE;


    public static void addPropertiesFile(String filename) throws PropertyFileNotFoundException {
        if (filename == null) {
            throw new IllegalArgumentException("addPropertiesFile(): filename is null");
        }

        filename = trimPropertiesFilename(filename);
        // Add the property file to the Map
        // If added already, return
        if (hasPropertiesFile(filename)) {
            return;
        }

        try {
            ResourceBundle bundle = ResourceBundle.getBundle(filename);
            bundles.put(filename, bundle);
        } catch (MissingResourceException e) {
            throw new PropertyFileNotFoundException("addPropertiesFile(): File " + filename + " not found");
        }
    }

    public static void addLocalPropertiesBundle() {
        try {
            File file = new File(PROPERTIES_DIRECTORY);
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_BUNDLE, Locale.getDefault(), loader);
            bundles.put(PROPERTIES_BUNDLE, bundle);
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

        for (String bundleName : bundles.keySet()) {
            ResourceBundle resourceBundle = bundles.get(bundleName);

            // find property with prefix first
            try {
                propertyValue = resourceBundle.getString(prefix + "." + propertyName);

                return propertyValue;
            } catch (MissingResourceException e) {
                //Ignore
            }
        }

        for (String bundleName : bundles.keySet()) {
            ResourceBundle resourceBundle = bundles.get(bundleName);

            try {
                propertyValue = resourceBundle.getString(propertyName);

                return propertyValue;
            } catch (MissingResourceException e) {
                //Ignore
            }
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

    public static String trimPropertiesFilename(String filename) {
        int index = filename.indexOf(".properties");
        if (index != -1) {
            filename = filename.substring(0, index);
        }

        return filename;
    }

    public static boolean hasPropertiesFile(String filename) {
        if (filename == null) {
            return false;
        } else {
            trimPropertiesFilename(filename);

            return bundles.get(filename) != null;
        }
    }
}
