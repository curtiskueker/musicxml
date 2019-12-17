package org.curtis.ui.task;

import java.util.Arrays;
import java.util.List;

public class TaskConstants {
    public static final String TASKS_TITLE = "MusicXml Tasks";

    public static final String CONVERSION_TYPE_MUSICXML = "MusicXml File";
    public static final String CONVERSION_TYPE_DATABASE = "Database Record";
    public static final String CONVERSION_TYPE_LILYPOND = "Lilypond File";
    public static final String CONVERSION_TYPE_PDF = "PDF File";

    public static final String OUTPUT_TO_CONSOLE = "To Console";
    public static final String OUTPUT_TO_FILE = "To File";
    public static final List<String> OUTPUT_TYPES = Arrays.asList(OUTPUT_TO_CONSOLE, OUTPUT_TO_FILE);

    private TaskConstants() {

    }
}
