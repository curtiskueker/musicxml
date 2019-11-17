package org.curtis.ui.javafx.handler;

import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.form.TaskForm;

public class LyPdfSettingsFormHandler extends FormHandler {
    public LyPdfSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        AppProperties.addLocalPropertiesBundle();
        taskForm.getTextField("lilypondLocation").setText(AppProperties.getOptionalProperty("location.lilypond"));
        taskForm.getTextField("pdfLocation").setText(AppProperties.getOptionalProperty("location.pdfreader"));
    }
}
