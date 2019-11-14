package org.curtis.ui.task;

import java.util.Arrays;
import java.util.List;

public class TaskConstants {
    public static final String TASKS_TITLE = "MusicXml Tasks";

    public static final String SHOW_PASSWORD = "Show Password";

    public static final String DATABASE_MYSQL = "mysql";
    public static final String DATABASE_POSTGRES = "postgresql";
    public static final String DATABASE_ORACLE = "oracle";
    public static final List<String> DATABASE_TYPES = Arrays.asList(DATABASE_MYSQL, DATABASE_POSTGRES, DATABASE_ORACLE);

    public static final String CONVERSION_TYPE_MUSICXML = "MusicXml File";
    public static final String CONVERSION_TYPE_DATABASE = "Database Record";
    public static final String CONVERSION_TYPE_LILYPOND = "Lilypond File";
    public static final String CONVERSION_TYPE_PDF = "PDF File";

    public static final String MENU_SET_PROPERTIES = "Set Properties";
    public static final String MENU_DATABASE_TASKS = "Database Tasks";
    public static final String MENU_CONVERSION_TASKS = "Conversion Tasks";
    public static final String MENU_SEPARATOR = "Menu Separator";
    public static final String MENU_EXIT_APPLICATION = "Exit";

    public static final String SUBMIT_BUTTON = "Submit";

    private TaskConstants() {

    }
}
