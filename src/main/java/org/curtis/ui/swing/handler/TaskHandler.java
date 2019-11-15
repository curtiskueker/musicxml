package org.curtis.ui.swing.handler;

import org.curtis.ui.swing.SwingTaskInitializer;
import org.curtis.ui.task.DatabaseTask;
import org.curtis.ui.task.Db2LyTask;
import org.curtis.ui.task.Db2MusicXmlTask;
import org.curtis.ui.task.Db2PdfTask;
import org.curtis.ui.task.Ly2PdfTask;
import org.curtis.ui.task.MusicXml2DbTask;
import org.curtis.ui.task.MusicXml2LyTask;
import org.curtis.ui.task.MusicXml2PdfTask;
import org.curtis.ui.task.MusicXmlTask;
import org.curtis.ui.task.SetPropertiesTask;
import org.curtis.ui.task.TaskConstants;
import org.curtis.ui.task.exception.TaskException;

import java.awt.Component;
import java.util.Map;

public class TaskHandler {
    private TaskHandler() {

    }

    public static void handleTask(Map<String, Component> componentMap, String menuSelection, String convertFromSelection, String convertToSelection) {
        MusicXmlTask musicXmlTask = null;
        SwingTaskInitializer swingTaskInitializer = new SwingTaskInitializer(componentMap);

        switch (menuSelection) {
            case TaskConstants.MENU_SET_PROPERTIES:
                musicXmlTask = new SetPropertiesTask(swingTaskInitializer);
                break;
            case TaskConstants.MENU_DATABASE_TASKS:
                musicXmlTask = new DatabaseTask(swingTaskInitializer);
                break;
            case TaskConstants.MENU_CONVERSION_TASKS:
                switch (convertFromSelection) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        switch (convertToSelection) {
                            case TaskConstants.CONVERSION_TYPE_DATABASE:
                                musicXmlTask = new MusicXml2DbTask(swingTaskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                musicXmlTask = new MusicXml2LyTask(swingTaskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                musicXmlTask = new MusicXml2PdfTask(swingTaskInitializer);
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        switch (convertToSelection) {
                            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                                musicXmlTask = new Db2MusicXmlTask(swingTaskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                musicXmlTask = new Db2LyTask(swingTaskInitializer);
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                musicXmlTask = new Db2PdfTask(swingTaskInitializer);
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        switch (convertToSelection) {
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                musicXmlTask = new Ly2PdfTask(swingTaskInitializer);
                                break;
                        }
                        break;
                }
                break;
        }

        try {
            if (musicXmlTask != null) musicXmlTask.execute();
            System.err.println("Task finished");
        } catch (TaskException e) {
            System.err.println(e.getMessage());
        }
    }
}
