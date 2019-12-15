package org.curtis.ui.javafx.handler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.javafx.form.FormNode;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertFormHandler extends FormHandler {
    private static final Pair<String, String> EMPTY_SELECTION = new Pair<>("", "");
    private static final Pair<String, String> MUSICXML_FROM_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_MUSICXML, FormNode.MUSICXML_FROM_BOX);
    private static final Pair<String, String> DB_FROM_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_DATABASE, FormNode.DB_FROM_BOX);
    private static final Pair<String, String> LY_FROM_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_LILYPOND, FormNode.LY_FROM_BOX);
    private static final Pair<String, String> MUSICXML_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_MUSICXML, FormNode.MUSICXML_TO_BOX);
    private static final Pair<String, String> DB_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_DATABASE, FormNode.DB_TO_BOX);
    private static final Pair<String, String> LY_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_LILYPOND, FormNode.LY_TO_BOX);
    private static final Pair<String, String> PDF_TO_SELECTION = new Pair<>(TaskConstants.CONVERSION_TYPE_PDF, FormNode.PDF_TO_BOX);
    private static final Pair<String, String> PDF_TO_OFF_SELECTION = new Pair<>("PDF File Off", FormNode.PDF_TO_OFF_BOX);
    private static final Pair<String, String> DELETE_SCORE_SELECTION = new Pair<>("Delete Score", FormNode.DELETE_SCORE_TO_BOX);
    private static final List<Pair<String, String>> FROM_SELECTIONS = new ArrayList<>(
            Arrays.asList(EMPTY_SELECTION, MUSICXML_FROM_SELECTION, DB_FROM_SELECTION, LY_FROM_SELECTION)
    );
    private static final List<Pair<String, String>> TO_SELECTIONS = new ArrayList<>(
            Arrays.asList(EMPTY_SELECTION, MUSICXML_TO_SELECTION, DB_TO_SELECTION, LY_TO_SELECTION, PDF_TO_SELECTION, PDF_TO_OFF_SELECTION, DELETE_SCORE_SELECTION)
    );
    private static final Map<Pair<String, String>, List<Pair<String, String>>> SELECTION_MAP = Map.ofEntries(
            Map.entry(EMPTY_SELECTION, new ArrayList<>(Collections.singletonList(EMPTY_SELECTION))),
            Map.entry(MUSICXML_FROM_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION, DB_TO_SELECTION, LY_TO_SELECTION, PDF_TO_SELECTION))),
            Map.entry(DB_FROM_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION, MUSICXML_TO_SELECTION, LY_TO_SELECTION, PDF_TO_SELECTION))),
            Map.entry(LY_FROM_SELECTION, new ArrayList<>(Arrays.asList(EMPTY_SELECTION, PDF_TO_SELECTION)))
    );
    private static Boolean FORM_INITIALIZED = false;
    private boolean resetScoreNames = true;

    public ConvertFormHandler(TaskForm taskForm) {
        super(taskForm);
    }

    public void initializeForm() {
        if (FORM_INITIALIZED) return;

        taskForm.setSelectList(FormNode.CONVERT_FROM_LIST, FROM_SELECTIONS.stream().map(Pair::getKey).collect(Collectors.toList()));
        ComboBox<String> convertToList = taskForm.getSelectList(FormNode.CONVERT_TO_LIST);
        convertToList.getItems().clear();

        showFromBox("");
        showToBox("");
        hideButton();

        FORM_INITIALIZED = true;
    }

    public void fromListSelected(String selectionName) {
        // set to select list based on from selection
        Pair<String, String> fromSelection = getSelectedPair(selectionName, FROM_SELECTIONS);
        taskForm.setSelectList(FormNode.CONVERT_TO_LIST, SELECTION_MAP.get(fromSelection).stream().map(Pair::getKey).collect(Collectors.toList()));

        showFromBox(fromSelection.getValue());

        if (selectionName.equals(TaskConstants.CONVERSION_TYPE_DATABASE)) setScoreNameFrom();
        else setResetScoreNames();
    }

    public void toListSelected(String selectionName) {
        Pair<String, String> toSelection = getSelectedPair(selectionName, TO_SELECTIONS);
        if (toSelection == null) showToBox("");
        else {
            if (toSelection == PDF_TO_SELECTION) {
                PropertiesHandler.addLocalPropertiesBundle();
                if (StringUtil.isEmpty(PropertiesHandler.getOptionalProperty(PropertiesConstants.LILYPOND_LOCATION))) toSelection = PDF_TO_OFF_SELECTION;
                else taskForm.handlePdfReaderDisplay();
            }
            showToBox(toSelection.getValue());
        }
    }

    public void scoreNameDeleteChecked(String scoreName, boolean checked) {
        if (checked) {
            taskForm.setSelectList(FormNode.CONVERT_TO_LIST, Collections.singletonList(DELETE_SCORE_SELECTION.getKey()), DELETE_SCORE_SELECTION.getKey());
            Label scoreLabel = (Label)taskForm.getNode(FormNode.DELETE_SCORE_LABEL_2);
            scoreLabel.setText(scoreName);
            showToBox(DELETE_SCORE_SELECTION.getValue());
        } else {
            fromListSelected(TaskConstants.CONVERSION_TYPE_DATABASE);
        }
    }

    private Pair<String, String> getSelectedPair(String selectionName, List<Pair<String, String>> selectionList) {
        return selectionList.stream().filter(pair -> pair.getKey().equals(selectionName)).findFirst().orElse(null);
    }

    private ComboBox<String> getScoreNameList() {
        return taskForm.getSelectList(FormNode.SCORE_NAME_FROM);
    }

    public void setScoreNameFrom() {
        if (!resetScoreNames) return;

        ComboBox<String> scoreNameFrom = getScoreNameList();
        ObservableList<String> scoreNames = FXCollections.observableArrayList(MusicXmlUtil.getScoreNames());
        scoreNameFrom.setItems(scoreNames);

        resetScoreNames = false;
    }

    public void setResetScoreNames() {
        resetScoreNames = true;
    }

    private void showFromBox(String boxName) {
        showBox(FROM_SELECTIONS, boxName);
    }

    private void showToBox(String boxName) {
        showBox(TO_SELECTIONS, boxName);
        getExecuteButton().setVisible(StringUtil.isNotEmpty(boxName) && !boxName.equals(FormNode.PDF_TO_OFF_BOX) && !boxName.equals(FormNode.DELETE_SCORE_TO_BOX));
        getDeleteButton().setVisible(StringUtil.isNotEmpty(boxName) && boxName.equals(FormNode.DELETE_SCORE_TO_BOX));
    }

    private void showBox(List<Pair<String, String>> boxes, String boxName) {
        for (Pair<String, String> box : boxes) {
            if (StringUtil.isEmpty(box.getValue())) continue;
            VBox vBox = (VBox)taskForm.getNode(box.getValue());
            if (vBox == null) continue;
            vBox.setVisible(box.getValue().equals(boxName));
        }
    }

    private void hideButton() {
        getExecuteButton().setVisible(false);
    }

    private Button getExecuteButton() {
        return (Button)taskForm.getNode(FormNode.EXECUTE_CONVERT_BUTTON);
    }

    private Button getDeleteButton() {
        return (Button)taskForm.getNode(FormNode.EXECUTE_DELETE_BUTTON);
    }
}
