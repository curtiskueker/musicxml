package org.curtis.ui.swing.handler;

import org.curtis.ui.input.DatabaseHandler;
import org.curtis.ui.input.Db2LyHandler;
import org.curtis.ui.input.Db2MusicXmlHandler;
import org.curtis.ui.input.Db2PdfHandler;
import org.curtis.ui.input.InputHandler;
import org.curtis.ui.input.Ly2PdfHandler;
import org.curtis.ui.input.MusicXml2DbHandler;
import org.curtis.ui.input.MusicXml2LyHandler;
import org.curtis.ui.input.MusicXml2PdfHandler;
import org.curtis.ui.input.SetPropertiesHandler;
import org.curtis.ui.swing.SwingTaskInitializer;
import org.curtis.ui.task.MusicXmlTask;
import org.curtis.ui.task.TaskConstants;
import org.curtis.ui.task.TaskException;

import java.awt.Component;
import java.util.Map;

public class TaskHandler {
    private TaskHandler() {

    }

    public static void handleTask(Map<String, Component> componentMap, String menuSelection, String convertFromSelection, String convertToSelection) {
        InputHandler inputHandler = null;
        SwingTaskInitializer swingTaskInitializer = new SwingTaskInitializer(componentMap);

        switch (menuSelection) {
            case FormHandler.MENU_SET_PROPERTIES:
                inputHandler = new SetPropertiesHandler();
                break;
            case FormHandler.MENU_DATABASE_TASKS:
                inputHandler = new DatabaseHandler();
                break;
            case FormHandler.MENU_CONVERSION_TASKS:
                switch (convertFromSelection) {
                    case TaskConstants.CONVERSION_TYPE_MUSICXML:
                        switch (convertToSelection) {
                            case TaskConstants.CONVERSION_TYPE_DATABASE:
                                inputHandler = new MusicXml2DbHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                inputHandler = new MusicXml2LyHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                inputHandler = new MusicXml2PdfHandler();
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_DATABASE:
                        switch (convertToSelection) {
                            case TaskConstants.CONVERSION_TYPE_MUSICXML:
                                inputHandler = new Db2MusicXmlHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_LILYPOND:
                                inputHandler = new Db2LyHandler();
                                break;
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                inputHandler = new Db2PdfHandler();
                                break;
                        }
                        break;
                    case TaskConstants.CONVERSION_TYPE_LILYPOND:
                        switch (convertToSelection) {
                            case TaskConstants.CONVERSION_TYPE_PDF:
                                inputHandler = new Ly2PdfHandler();
                                break;
                        }
                        break;
                }
                break;
        }

        try {
            if (inputHandler != null) {
                MusicXmlTask musicXmlTask = new MusicXmlTask(swingTaskInitializer, inputHandler);
                musicXmlTask.execute();
            }
            System.err.println("Task finished");
        } catch (TaskException e) {
            System.err.println(e.getMessage());
        }
    }
}
