package org.curtis.ui.javafx.form;

import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.TasksController;

public class LyPdfSettingsFormHandler extends FormHandler {
    public LyPdfSettingsFormHandler(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeForm() {
        AppProperties.addLocalPropertiesBundle();
        tasksController.getTextField("lilypondLocation").setText(AppProperties.getOptionalProperty("location.lilypond"));
        tasksController.getTextField("pdfLocation").setText(AppProperties.getOptionalProperty("location.pdfreader"));
    }
}
