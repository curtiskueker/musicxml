package org.curtis.ui.javafx.form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.curtis.ui.javafx.TasksController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertFormHandler extends FormHandler {
    private static final String[] CONVERT_TYPES = {"", "MusicXml File", "Database Record", "Lilypond File", "PDF File"};
    private static final List<String> FROM_BOXES = new ArrayList<>(Arrays.asList("musicXmlFromBox", "dbFromBox", "lyFromBox"));
    private static final List<String> TO_BOXES = new ArrayList<>(Arrays.asList("musicXmlToBox", "dbToBox", "lyToBox", "pdfToBox"));

    public ConvertFormHandler(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeForm() {
        ComboBox convertFromList = (ComboBox)tasksController.getNode("convertFromList");
        ObservableList<String> convertFromTypes = FXCollections.observableArrayList(CONVERT_TYPES);
        convertFromList.setItems(convertFromTypes);

        ComboBox convertToList = (ComboBox)tasksController.getNode("convertToList");
        convertToList.getItems().clear();

        showFromBox("");
        showToBox("");
        showButton(false);
    }

    private void showFromBox(String boxName) {
        showBox(FROM_BOXES, boxName);
    }

    private void showToBox(String boxName) {
        showBox(TO_BOXES, boxName);
    }

    private void showBox(List<String> boxes, String boxName) {
        for (String box : boxes) tasksController.getNode(box).setVisible(box.equals(boxName));
    }

    private void showButton(boolean show) {
        getExecuteButton().setVisible(show);
    }

    private Button getExecuteButton() {
        return (Button)tasksController.getNode("executeConvert");
    }
}
