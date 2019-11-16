package org.curtis.ui.javafx.handler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.TasksController;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertFormHandler extends FormHandler {
    private static final Pair<String, String> EMPTY_SELECTION = new Pair<>("", "");
    private static final Pair<String, String> MUSICXML_FROM_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_MUSICXML, "musicXmlFromBox");
    private static final Pair<String, String> DB_FROM_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_DATABASE, "dbFromBox");
    private static final Pair<String, String> LY_FROM_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_LILYPOND, "lyFromBox");
    private static final Pair<String, String> MUSICXML_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_MUSICXML, "musicXmlToBox");
    private static final Pair<String, String> DB_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_DATABASE, "dbToBox");
    private static final Pair<String, String> LY_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_LILYPOND, "lyToBox");
    private static final Pair<String, String> PDF_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_PDF, "pdfToBox");
    private static final Pair<String, String> PDF_TO_OFF_SELECTION = new Pair<>("PDF File Off", "pdfToOffBox");
    private static final List<Pair<String, String>> FROM_SELECTIONS = new ArrayList<>(
            Arrays.asList(EMPTY_SELECTION, MUSICXML_FROM_SELECTION, DB_FROM_SELECTION, LY_FROM_SELECTION)
    );
    private static final List<Pair<String, String>> TO_SELECTIONS = new ArrayList<>(
            Arrays.asList(EMPTY_SELECTION, MUSICXML_TO_SELECTION, DB_TO_SELECTION, LY_TO_SELECTION, PDF_TO_SELECTION, PDF_TO_OFF_SELECTION)
    );
    private static final Map<Pair<String, String>, List<Pair<String, String>>> SELECTION_MAP = Map.ofEntries(
            Map.entry(EMPTY_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION))),
            Map.entry(MUSICXML_FROM_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION, DB_TO_SELECTION, LY_TO_SELECTION, PDF_TO_SELECTION))),
            Map.entry(DB_FROM_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION, MUSICXML_TO_SELECTION, LY_TO_SELECTION, PDF_TO_SELECTION))),
            Map.entry(LY_FROM_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION, PDF_TO_SELECTION)))
    );
    private static Boolean FORM_INITIALIZED = false;

    public ConvertFormHandler(TasksController tasksController) {
        super(tasksController);
    }

    public void initializeForm() {
        if (FORM_INITIALIZED) return;

        ComboBox<String> convertFromList = (ComboBox)tasksController.getNode("convertFromList");
        ObservableList<String> convertFromTypes = FXCollections.observableArrayList(FROM_SELECTIONS.stream().map(Pair::getKey).collect(Collectors.toList()));
        convertFromList.setItems(convertFromTypes);

        ComboBox convertToList = (ComboBox)tasksController.getNode("convertToList");
        convertToList.getItems().clear();

        showFromBox("");
        showToBox("");
        showButton(false);

        FORM_INITIALIZED = true;
    }

    public void fromListSelected(String selectionName) {
        // set to select list based on from selection
        Pair<String, String> fromSelection = getSelectedPair(selectionName, FROM_SELECTIONS);

        ComboBox<String> convertToList = (ComboBox)tasksController.getNode("convertToList");

        List<Pair<String, String>> toPairs = SELECTION_MAP.get(fromSelection);
        ObservableList<String> convertToTypes = FXCollections.observableArrayList(toPairs.stream().map(Pair::getKey).collect(Collectors.toList()));
        convertToList.setItems(convertToTypes);

        showFromBox(fromSelection.getValue());

        if (selectionName.equals(TaskConstants.CONVERSION_TYPE_DATABASE)) setScoreNameFrom();
        else clearScoreNameFrom();
    }

    public void toListSelected(String selectionName) {
        Pair<String, String> toSelection = getSelectedPair(selectionName, TO_SELECTIONS);
        if (toSelection == null) showToBox("");
        else {
            if (toSelection == PDF_TO_SELECTION) {
                AppProperties.addLocalPropertiesBundle();
                if (StringUtil.isEmpty(AppProperties.getOptionalProperty("location.lilypond"))) toSelection = PDF_TO_OFF_SELECTION;
                else tasksController.handlePdfReaderDisplay();
            }
            showToBox(toSelection.getValue());
        }
    }

    private Pair<String, String> getSelectedPair(String selectionName, List<Pair<String, String>> selectionList) {
        return selectionList.stream().filter(pair -> pair.getKey().equals(selectionName)).findFirst().orElse(null);
    }

    private void setScoreNameFrom() {
        ComboBox<String> scoreNameFrom = (ComboBox)tasksController.getNode("scoreNameFrom");
        if (!scoreNameFrom.getItems().isEmpty()) return;

        ObservableList<String> scoreNames = FXCollections.observableArrayList(MusicXmlUtil.getScoreNames());
        scoreNameFrom.setItems(scoreNames);
    }

    private void clearScoreNameFrom() {
        ComboBox<String> scoreNameFrom = (ComboBox)tasksController.getNode("scoreNameFrom");
        scoreNameFrom.getItems().clear();
    }

    private void showFromBox(String boxName) {
        showBox(FROM_SELECTIONS, boxName);
    }

    private void showToBox(String boxName) {
        showBox(TO_SELECTIONS, boxName);
        Button executeConvert = (Button)tasksController.getNode("executeConvert");
        executeConvert.setVisible(StringUtil.isNotEmpty(boxName) && !boxName.equals("pdfToOffBox"));
    }

    private void showBox(List<Pair<String, String>> boxes, String boxName) {
        for (Pair<String, String> box : boxes) {
            if (StringUtil.isEmpty(box.getValue())) continue;
            VBox vBox = (VBox)tasksController.getNode(box.getValue());
            if (vBox == null) continue;
            vBox.setVisible(box.getValue().equals(boxName));
        }
    }

    private void showButton(boolean show) {
        getExecuteButton().setVisible(show);
    }

    private Button getExecuteButton() {
        return (Button)tasksController.getNode("executeConvert");
    }
}
