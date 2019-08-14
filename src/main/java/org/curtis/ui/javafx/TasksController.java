package org.curtis.ui.javafx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TasksController {
    private StatusOutput statusOutput;
    private static final List<String> fromBoxes = new ArrayList<>(Arrays.asList("musicXmlFromBox", "dbFromBox", "lyFromBox"));
    private static final List<String> toBoxes = new ArrayList<>(Arrays.asList("musicXmlToBox", "dbToBox", "lyToBox", "pdfToBox"));

    @FXML
    private VBox taskBox;

    @FXML
    private TextArea statusTextArea;

    @FXML
    public void initialize() {
        // Setup status output box
        statusOutput = new StatusOutput(statusTextArea);
        PrintStream statusPrintStream = new PrintStream(statusOutput);
        System.setErr(statusPrintStream);
        System.setOut(statusPrintStream);
    }

    public Scene getScene() {
        return taskBox.getScene();
    }

    public Stage getStage() {
        return (Stage)getScene().getWindow();
    }

    public Node getNode(String nodeName) {
        return getScene().lookup("#" + nodeName);
    }

    public TextField getTextField(String nodeName) {
        return (TextField)getNode(nodeName);
    }

    public StatusOutput getStatusOutput() {
        return statusOutput;
    }

    @FXML
    private void buttonPressed(ActionEvent actionEvent) {
        statusOutput.clear();

        Button button = (Button)actionEvent.getSource();

        // Run task in thread
        TaskExecutor taskExecutor = new TaskExecutor(button.getId(), this);
        Runnable taskRunnable = taskExecutor::execute;
        Thread formThread = new Thread(taskRunnable);
        formThread.start();
    }

    @FXML
    private void tabSelected(Event actionEvent) {
        Tab tab = (Tab)actionEvent.getSource();
        if (!tab.isSelected()) return;

        TaskExecutor taskExecutor = new TaskExecutor(tab.getId(), this);
        taskExecutor.initializeForm();
    }

    @FXML
    private void showPassword() {
        String passwordNodeName = "dbPassword";
        String passwordShowNodeName = "dbPasswordShow";
        boolean showPassword = checkboxOn("showPassword");
        if (showPassword) togglePassword(passwordNodeName, passwordShowNodeName);
        else togglePassword(passwordShowNodeName, passwordNodeName);
    }

    private void togglePassword(String fromNodeName, String toNodeName) {
        TextInputControl fromTextInput = (TextInputControl)getNode(fromNodeName);
        TextInputControl toTextInput = (TextInputControl)getNode(toNodeName);
        toTextInput.setText(fromTextInput.getText());
        fromTextInput.setVisible(false);
        toTextInput.setVisible(true);
    }

    @FXML
    private void showSchemaFileLocation() {
        boolean showSchemaLocation = checkboxOn("generateSchema");
        Node chooseSchemaLocationLink = getNode("chooseSchemaLocationLink");
        chooseSchemaLocationLink.setVisible(showSchemaLocation);
        Node schemaFileLocation = getNode("schemaFileLocation");
        schemaFileLocation.setVisible(showSchemaLocation);
    }

    @FXML
    private void chooseSchemaLocation() {
        FileChooser schemaLocationFileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SQL Files (*.sql)", "*.sql");
        schemaLocationFileChooser.getExtensionFilters().add(extFilter);
        File file = schemaLocationFileChooser.showSaveDialog(getStage());
        if(file != null){
            TextField schemaFileLocation = getTextField("schemaFileLocation");
            schemaFileLocation.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void showMusicXmlFrom() {
        showFromBox("musicXmlFromBox");
    }

    @FXML
    private void showDbFrom() {
        showFromBox("dbFromBox");
    }

    @FXML
    private void showLyFrom() {
        showFromBox("lyFromBox");
    }

    @FXML
    private void showMusicXmlTo() {
        showToBox("musicXmlToBox");
    }

    @FXML
    private void showDbTo() {
        showToBox("dbToBox");
    }

    @FXML
    private void showLyTo() {
        showToBox("lyToBox");
    }

    @FXML
    private void showPdfTo() {
        showToBox("pdfToBox");
    }

    @FXML
    private void chooseMusicXmlFromFile() {
        setFileLocationInTextField("musicXmlFromFile");
    }

    @FXML
    private void chooseLyFromFile() {
        setFileLocationInTextField("lyFromFile");
    }

    @FXML
    private void chooseMusicXmlToFile() {
        setFileLocationInTextField("musicXmlToFile");
    }

    @FXML
    private void chooseLyToFile() {
        setFileLocationInTextField("lyToFile");
    }

    @FXML
    private void choosePdfToFile() {
        setFileLocationInTextField("pdfToFile");
    }

    @FXML
    private void executeConvert() {
        buttonSelected("Convert action executed");
    }

    @FXML
    private void executeTables() {
        buttonSelected("Database action executed");
    }

    @FXML
    private void setLilypondLocation() {
        setFileLocationInTextField("lilypondLocation");
    }

    @FXML
    private void setPdfLocation() {
        setFileLocationInTextField("pdfLocation");
    }

    @FXML
    private void setValidateLocation() {
        setFileLocationInTextField("validateLocation");
    }

    @FXML
    private void executeValidate() {
        buttonSelected("Validate action executed");
    }

    private void setFileLocationInTextField(String textFieldName) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(getStage());
        if (file == null) return;

        TextField textField = getTextField(textFieldName);
        textField.setText(file.getAbsolutePath());
    }

    public boolean checkboxOn(String controlName) {
        CheckBox checkBox = (CheckBox)getNode(controlName);

        return checkBox.isSelected();
    }

    private void showFromBox(String boxName) {
        showBox(fromBoxes, boxName);
    }

    private void showToBox(String boxName) {
        showBox(toBoxes, boxName);
    }

    private void showBox(List<String> boxes, String boxName) {
        for (String box : boxes) getNode(box).setVisible(box.equals(boxName));
    }

    private void buttonSelected(String text) {
        statusOutput.clear();
        System.err.println(text);
    }
}
