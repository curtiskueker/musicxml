package org.curtis.properties;

import org.curtis.util.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class AppProperties {
    private static Map<String, ResourceBundle> bundles = new HashMap<>();

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

    public static String getString(String propertyName) throws PropertyException {
        if (StringUtil.isEmpty(propertyName)) {
            throw new PropertyException("Property name is empty");
        }

        String propertyValue;

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
            throw new RequiredPropertyNotFoundException(e);
        }
        return propertyValue;
    }

    public static int getInt(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);

        try {
            return Integer.parseInt(propertyValue);
        } catch (NumberFormatException e) {
            throw new PropertyException("getInt(): Property " + propertyName + " value not an int");
        }
    }

    public static long getLong(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);

        try {
            return Long.parseLong(propertyValue);
        } catch (NumberFormatException e) {
            throw new PropertyException("Properties.getLong(): Property " + propertyName + " value not a long");
        }
    }

    public static float getFloat(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);
        try {
            return Float.parseFloat(propertyValue);
        } catch (NumberFormatException e) {
            throw new PropertyException("getLong(): Property " + propertyName + " value not a long");
        }
    }

    public static boolean getBoolean(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);

        return propertyValue.equals("1") || Boolean.parseBoolean(propertyValue);
    }

    public static String[] getStringArray(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);
        try {
            return propertyValue.split(",");
        } catch (NumberFormatException e) {
            throw new PropertyException("Properties.getLong(): Property " + propertyName + " value not a String[]");
        }
    }

    public static List getList(String propertyName) throws PropertyException {
        String propertyValue = getString(propertyName);
        try {
            return Arrays.asList(propertyValue.split(","));
        } catch (NumberFormatException e) {
            throw new PropertyException("Properties.getLong(): Property " + propertyName + " value not a List");
        }
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
