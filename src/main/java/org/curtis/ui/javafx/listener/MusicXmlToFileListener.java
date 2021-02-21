package org.curtis.ui.javafx.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.util.FileUtil;

public class MusicXmlToFileListener implements ChangeListener<String> {
    private TaskForm taskForm;
    private VBox musicXmlToZippedBox;

    public MusicXmlToFileListener(TaskForm taskForm) {
        this.taskForm = taskForm;

        setupListener();
    }

    private void setupListener() {
        musicXmlToZippedBox = (VBox)taskForm.getNode(FormNode.MUSICXML_TO_ZIPPED_BOX);
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String fromValue, String toValue) {
        musicXmlToZippedBox.setVisible(FileUtil.isCompressedMusicXmlFileExtension(toValue));
    }
}
