package org.curtis.ui.javafx.form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.Pair;
import org.curtis.ui.javafx.TasksController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertFormHandler extends FormHandler {
    private static final List<Pair<String, String>> FROM_SELECTIONS = new ArrayList<>(
            Arrays.asList(
                    new Pair<>("", ""),
                    new Pair<>("MusicXml File", "musicXmlFromBox"),
                    new Pair<>("Database Record", "dbFromBox"),
                    new Pair<>("Lilypond File", "lyFromBox")
            )
    );
    private static final List<Pair<String, String>> TO_SELECTIONS = new ArrayList<>(
            Arrays.asList(
                    new Pair<>("", ""),
                    new Pair<>("MusicXml File", "musicXmlToBox"),
                    new Pair<>("Database Record", "dbToBox"),
                    new Pair<>("Lilypond File", "lyToBox"),
                    new Pair<>("PDF File", "pdfToBox")
            )
    );

    public ConvertFormHandler(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeForm() {
        ComboBox convertFromList = (ComboBox)tasksController.getNode("convertFromList");
        ObservableList<String> convertFromTypes = FXCollections.observableArrayList(FROM_SELECTIONS.stream().map(Pair::getKey).collect(Collectors.toList()));
        convertFromList.setItems(convertFromTypes);

        ComboBox convertToList = (ComboBox)tasksController.getNode("convertToList");
        convertToList.getItems().clear();

        showFromBox("");
        showToBox("");
        showButton(false);
    }

    private void showFromBox(String boxName) {
        showBox(FROM_SELECTIONS, boxName);
    }

    private void showToBox(String boxName) {
        showBox(TO_SELECTIONS, boxName);
    }

    private void showBox(List<Pair<String, String>> boxes, String boxName) {
        for (Pair<String, String> box : boxes) tasksController.getNode(box.getValue()).setVisible(box.getKey().equals(boxName));
    }

    private void showButton(boolean show) {
        getExecuteButton().setVisible(show);
    }

    private Button getExecuteButton() {
        return (Button)tasksController.getNode("executeConvert");
    }
}
