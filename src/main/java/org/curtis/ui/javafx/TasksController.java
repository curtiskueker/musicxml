package org.curtis.ui.javafx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.curtis.ui.javafx.form.TaskForm;

public class TasksController {
    private TaskForm taskForm;

    @FXML
    private VBox taskBox;

    @FXML
    private TextArea statusTextArea;

    @FXML
    public void initialize() {
        taskForm = new TaskForm();
        taskForm.setTaskBox(taskBox);
        taskForm.setStatusTextArea(statusTextArea);
        taskForm.initialize();
    }

    public void cleanup() {
        taskForm.cleanup();
    }

    public Scene getScene() {
        if (taskBox == null) return null;

        return taskBox.getScene();
    }

    @FXML
    private void buttonPressed(ActionEvent actionEvent) {
        taskForm.clearOutput();

        Button button = (Button)actionEvent.getSource();

        // Run task in thread
        TaskExecutor taskExecutor = new TaskExecutor(button.getId(), taskForm);
        Runnable taskRunnable = taskExecutor::execute;
        Thread formThread = new Thread(taskRunnable);
        formThread.start();
    }

    @FXML
    private void tabSelected(Event actionEvent) {
        if (getScene() == null) return;

        Tab tab = (Tab)actionEvent.getSource();
        if (!tab.isSelected()) return;

        TaskExecutor taskExecutor = new TaskExecutor(tab.getId(), taskForm);
        taskExecutor.initializeForm();
    }

    @FXML
    private void fromListSelected(ActionEvent actionEvent) {
        ComboBox comboBox = (ComboBox)actionEvent.getSource();
        taskForm.fromListSelected((String)comboBox.getValue());
    }

    @FXML
    private void toListSelected(ActionEvent actionEvent) {
        ComboBox comboBox = (ComboBox)actionEvent.getSource();
        taskForm.toListSelected((String)comboBox.getValue());
    }

    @FXML
    private void showPassword() {
        taskForm.showPassword();
    }

    @FXML
    private void showSchemaFileLocation() {
        taskForm.showSchemaFileLocation();
    }

    @FXML
    private void chooseSchemaLocation() {
        taskForm.setChooseFileLocationInTextField("schemaFileLocation", "SQL Files (*.sql)", "*.sql");
    }

    @FXML
    private void setConvertFromList() {
        ComboBox<String> convertFromList = taskForm.getSelectList("convertFromList");
        if (!convertFromList.getItems().isEmpty()) return;

        taskForm.initializeConvertForm();
    }

    @FXML
    private void chooseMusicXmlFromFile() {
        taskForm.setOpenFileLocationInTextField("musicXmlFromFile");
    }

    @FXML
    private void chooseLyFromFile() {
        taskForm.setOpenFileLocationInTextField("lyFromFile");
    }

    @FXML
    private void chooseMusicXmlToFile() {
        taskForm.setChooseFileLocationInTextField("musicXmlToFile", "XML Files (*.xml)", "*.xml");
    }

    @FXML
    private void chooseLyToFile() {
        taskForm.setChooseFileLocationInTextField("lyToFile", "Lilypond Files (*.ly)", "*.ly");
    }

    @FXML
    private void choosePdfToFile() {
        taskForm.setChooseFileLocationInTextField("pdfToFile", "No extension", "");
    }

    @FXML
    private void setLilypondLocation() {
        taskForm.setOpenFileLocationInTextField("lilypondLocation");
    }

    @FXML
    private void setPdfLocation() {
        taskForm.setOpenFileLocationInTextField("pdfLocation");
    }

    @FXML
    private void setValidateLocation() {
        taskForm.setOpenFileLocationInTextField("validateLocation");
    }

    @FXML
    private void scoreNameSelected() {
        taskForm.scoreNameSelected();
    }

    @FXML
    private void scoreNameDeleteChecked() {
        taskForm.scoreNameDeleteChecked();
    }

    @FXML
    private void showSql(ActionEvent actionEvent) {
        taskForm.showSql(((Node)actionEvent.getSource()).getId());
    }
}
