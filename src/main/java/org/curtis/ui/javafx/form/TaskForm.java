package org.curtis.ui.javafx.form;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.ui.javafx.status.StatusOutput;
import org.curtis.ui.javafx.handler.ConvertFormHandler;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TaskForm {
    // The outermost Container
    private VBox taskBox;

    private StatusOutput statusOutput;
    private TextArea statusTextArea;
    private ConvertFormHandler convertFormHandler;

    private static final List<String> ACTIVATABLE_NODES = Arrays.asList(
            FormNode.EXECUTE_CONVERT_BUTTON,
            FormNode.EXECUTE_DELETE_BUTTON,
            FormNode.SAVE_DB_SETTINGS_BUTTON,
            FormNode.SAVE_LY_PDF_SETTINGS_BUTTON,
            FormNode.SAVE_OUTPUT_SETTINGS_BUTTON,
            FormNode.EXECUTE_DB_ACTIONS_BUTTON,
            FormNode.EXECUTE_VALIDATE_BUTTON,
            FormNode.SHOW_SQL_FROM,
            FormNode.SHOW_SQL_TO,
            FormNode.SCORE_NAME_DELETE,
            FormNode.SKIP_COMMENTS,
            FormNode.INCLUDE_PAGE_BREAKS
    );

    private Thread outputThread;

    public TaskForm() {

    }

    public void setTaskBox(VBox taskBox) {
        this.taskBox = taskBox;
    }

    public void setStatusTextArea(TextArea statusTextArea) {
        this.statusTextArea = statusTextArea;
    }

    public void initialize() {
        statusOutput = new StatusOutput(statusTextArea);

        Runnable outputRunnable = statusOutput::handle;
        outputThread = new Thread(outputRunnable);
        outputThread.start();

        convertFormHandler = new ConvertFormHandler(this);
    }

    public void cleanup() {
        if (outputThread != null) outputThread.interrupt();
    }

    public void clearOutput() {
        statusOutput.clear();
    }

    public void resetOutputStream() {
        statusOutput.resetOutputHandler();
    }

    public Node getNode(String nodeName) {
        return getScene().lookup("#" + nodeName);
    }

    private Stage getStage() {
        return (Stage)getScene().getWindow();
    }

    private Scene getScene() {
        return taskBox.getScene();
    }

    public ComboBox<String> getSelectList(String nodeName) {
        return (ComboBox<String>)getNode(nodeName);
    }

    public String getSelectListValue(String nodeName) {
        return getSelectList(nodeName).getValue();
    }

    public ComboBox<String> setSelectList(String nodeName, List<String> selectItems) {
        ComboBox<String> comboBox = getSelectList(nodeName);
        comboBox.setItems(FXCollections.observableArrayList(selectItems));

        return comboBox;
    }

    public void setSelectList(String nodeName, List<String> selectItems, String selectedItem) {
        ComboBox<String> comboBox = setSelectList(nodeName, selectItems);
        comboBox.setValue(selectedItem);
    }

    public TextField getTextField(String nodeName) {
        return (TextField)getNode(nodeName);
    }

    private CheckBox getCheckbox(String nodeName) {
        return (CheckBox)getNode(nodeName);
    }

    public String getFromSelection() {
        return getSelectListValue(FormNode.CONVERT_FROM_LIST);
    }

    public String getToSelection() {
        return getSelectListValue(FormNode.CONVERT_TO_LIST);
    }

    public void fromListSelected(String value) {
        convertFormHandler.fromListSelected(value);
    }

    public void toListSelected(String value) {
        convertFormHandler.toListSelected(value);
    }

    public boolean checkboxOn(String controlName) {
        CheckBox checkBox = getCheckbox(controlName);

        return checkBox.isSelected();
    }

    public void scoreNameSelected() {
        CheckBox scoreNameDeleteCheckBox = getScoreNameDeleteCheckbox();
        scoreNameDeleteCheckBox.setVisible(StringUtil.isNotEmpty(getScoreNameSelection()));
        scoreNameDeleteCheckBox.setSelected(false);
        if (getFromSelection().equals(TaskConstants.CONVERSION_TYPE_DATABASE)) convertFormHandler.scoreNameDeleteChecked("", false);
    }

    private String getScoreNameSelection() {
        return getSelectListValue(FormNode.SCORE_NAME_FROM);
    }

    public void scoreNameDeleteChecked() {
        convertFormHandler.scoreNameDeleteChecked(getScoreNameSelection(), getScoreNameDeleteCheckbox().isSelected());
    }

    private CheckBox getScoreNameDeleteCheckbox() {
        return getCheckbox(FormNode.SCORE_NAME_DELETE);
    }

    public void setResetScoreNames() {
        convertFormHandler.setResetScoreNames();
    }

    private void handleResetScoreNames() {
        convertFormHandler.setScoreNameFrom();
    }

    public void showSql(String controlName) {
        boolean checkboxOn = checkboxOn(controlName);

        getCheckbox(FormNode.SHOW_SQL_FROM).setSelected(checkboxOn);
        getCheckbox(FormNode.SHOW_SQL_TO).setSelected(checkboxOn);

        statusOutput.printToOutputStream(checkboxOn);
    }

    public void showPassword() {
        boolean showPassword = checkboxOn(FormNode.SHOW_PASSWORD);
        if (showPassword) togglePassword(FormNode.DB_PASSWORD, FormNode.DB_PASSWORD_SHOW);
        else togglePassword(FormNode.DB_PASSWORD_SHOW, FormNode.DB_PASSWORD);
    }

    private void togglePassword(String fromNodeName, String toNodeName) {
        TextInputControl fromTextInput = (TextInputControl)getNode(fromNodeName);
        TextInputControl toTextInput = (TextInputControl)getNode(toNodeName);
        toTextInput.setText(fromTextInput.getText());
        fromTextInput.setVisible(false);
        toTextInput.setVisible(true);
    }

    public void outputTypeSelected(String controlName, String value) {
        if (StringUtil.isEmpty(value)) return;

        if (controlName.equals(FormNode.TASK_OUTPUT_TYPE)) {
            getNode(FormNode.CHOOSE_TASK_OUTPUT_LOCATION).setVisible(value.equals(TaskConstants.OUTPUT_TO_FILE));
            getNode(FormNode.TASK_OUTPUT_LOCATION).setVisible(value.equals(TaskConstants.OUTPUT_TO_FILE));
        } else if (controlName.equals(FormNode.SQL_OUTPUT_TYPE)) {
            getNode(FormNode.CHOOSE_SQL_OUTPUT_LOCATION).setVisible(value.equals(TaskConstants.OUTPUT_TO_FILE));
            getNode(FormNode.SQL_OUTPUT_LOCATION).setVisible(value.equals(TaskConstants.OUTPUT_TO_FILE));
        }
    }

    public void showSchemaFileLocation() {
        boolean showSchemaLocation = checkboxOn(FormNode.GENERATE_SCHEMA);
        Node chooseSchemaLocationLink = getNode(FormNode.CHOOSE_SCHEMA_LOCATION);
        chooseSchemaLocationLink.setVisible(showSchemaLocation);
        Node schemaFileLocation = getNode(FormNode.SCHEMA_FILE_LOCATION);
        schemaFileLocation.setVisible(showSchemaLocation);
    }

    public void setOpenFileLocationInTextField(String textFieldName) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(getStage());
        if (file == null) return;

        TextField textField = getTextField(textFieldName);
        textField.setText(file.getAbsolutePath());
    }

    public void setChooseFileLocationInTextField(String textFieldName, String extensionLabel, String ... extensionFilters) {
        FileChooser fileChooser = new FileChooser();
        if (extensionFilters.length > 0) {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(extensionLabel, extensionFilters);
            fileChooser.getExtensionFilters().add(extFilter);
        }
        File file = fileChooser.showSaveDialog(getStage());
        if(file != null){
            TextField fileLocation = getTextField(textFieldName);
            fileLocation.setText(file.getAbsolutePath());
        }
    }

    public void initializeConvertForm() {
        convertFormHandler.initializeForm();
    }

    public void handleLyPdfUpdates() {
        String toSelection = getToSelection();
        if (toSelection == null) return;

        if (TaskConstants.CONVERSION_TYPE_PDF.equals(toSelection)) {
            if (StringUtil.isEmpty(PropertiesHandler.getOptionalProperty(PropertiesConstants.LILYPOND_LOCATION))) convertFormHandler.showPdfOffBox();
            else convertFormHandler.showPdfBox();
            handlePdfReaderDisplay();
        }
    }

    public void handleDisplayUpdates() {
        handleResetScoreNames();
        handlePdfReaderDisplay();
    }

    public void handlePdfReaderDisplay() {
        boolean pdfReaderSet = StringUtil.isNotEmpty(PropertiesHandler.getOptionalProperty(PropertiesConstants.PDF_LOCATION));
        getNode(FormNode.OPEN_PDF).setVisible(pdfReaderSet);
        getNode(FormNode.OPEN_PDF_LABEL_1).setVisible(!pdfReaderSet);
        getNode(FormNode.OPEN_PDF_LABEL_2).setVisible(!pdfReaderSet);
        getNode(FormNode.OPEN_PDF_LABEL_3).setVisible(!pdfReaderSet);
    }

    public void disableNodes(boolean disable) {
        for (String nodeName : ACTIVATABLE_NODES) getNode(nodeName).setDisable(disable);
    }
}
