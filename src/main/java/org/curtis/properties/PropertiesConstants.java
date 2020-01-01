package org.curtis.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertiesConstants {
    public static final String PROPERTIES_PREFIX = "musicxml";

    public static final String DB_USERNAME = "database.username";
    public static final String DB_PASSWORD = "database.password";
    public static final String DB_NAME = "database.name";
    public static final String DB_SERVER = "database.server";
    public static final String DB_TYPE = "database.type";
    public static final String LILYPOND_LOCATION = "location.lilypond";
    public static final String PDF_LOCATION = "location.pdfreader";
    public static final String TASK_OUTPUT_TYPE = "output.task.type";
    public static final String TASK_OUTPUT_LOCATION = "output.task.location";
    public static final String SQL_OUTPUT_TYPE = "output.sql.type";
    public static final String SQL_OUTPUT_LOCATION = "output.sql.location";

    private static final String DISPLAY_PROPERTY_SUFFIX = ".display";

    private static final List<String> USER_INPUT_PROPERTIES = new ArrayList<>(
            Arrays.asList(
                    PROPERTIES_PREFIX + "." + DB_USERNAME,
                    PROPERTIES_PREFIX + "." + DB_PASSWORD,
                    PROPERTIES_PREFIX + "." + DB_NAME,
                    PROPERTIES_PREFIX + "." + DB_SERVER,
                    LILYPOND_LOCATION,
                    PDF_LOCATION,
                    TASK_OUTPUT_LOCATION,
                    SQL_OUTPUT_LOCATION
            )
    );

    private PropertiesConstants() {

    }

    public static String getDisplayProperty(String inputProperty) {
        if (!USER_INPUT_PROPERTIES.contains(inputProperty)) return "";

        return inputProperty + DISPLAY_PROPERTY_SUFFIX;
    }
}
