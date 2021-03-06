package org.curtis.ui.javafx.handler;

import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;

public class LyPdfSettingsFormHandler extends FormHandler {
    public LyPdfSettingsFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        PropertiesHandler.addLocalProperties();
        taskForm.getTextField(FormNode.LILYPOND_LOCATION).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(PropertiesConstants.LILYPOND_LOCATION)));
        taskForm.getTextField(FormNode.PDF_LOCATION).setText(PropertiesHandler.getOptionalProperty(PropertiesConstants.getDisplayProperty(PropertiesConstants.PDF_LOCATION)));
    }
}
