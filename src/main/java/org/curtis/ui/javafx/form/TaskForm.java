package org.curtis.ui.javafx.form;

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
import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.StatusOutput;
import org.curtis.ui.javafx.handler.ConvertFormHandler;
import org.curtis.util.StringUtil;

import java.io.File;
import java.io.PrintStream;

public class TaskForm {
    // The outermost Container
    private VBox taskBox;
    private StatusOutput statusOutput;
    private TextArea statusTextArea;
    private ConvertFormHandler convertFormHandler;
    private boolean resetScoreNames = false;

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
        PrintStream statusPrintStream = new PrintStream(statusOutput);
        System.setErr(statusPrintStream);
        System.setOut(statusPrintStream);

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

    public TextField getTextField(String nodeName) {
        return (TextField)getNode(nodeName);
    }

    private CheckBox getCheckbox(String nodeName) {
        return (CheckBox)getNode(nodeName);
    }

    public String getFromSelection() {
        ComboBox<String> fromList = getSelectList("convertFromList");
        return fromList.getValue();
    }

    public String getToSelection() {
        ComboBox<String> toList = getSelectList("convertToList");
        return toList.getValue();
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
        convertFormHandler.scoreNameDeleteChecked("", false);
    }

    private String getScoreNameSelection() {
        return getSelectList("scoreNameFrom").getValue();
    }

    public void scoreNameDeleteChecked() {
        convertFormHandler.scoreNameDeleteChecked(getScoreNameSelection(), getScoreNameDeleteCheckbox().isSelected());
    }

    private CheckBox getScoreNameDeleteCheckbox() {
        return getCheckbox("scoreNameDelete");
    }

    public void setResetScoreNames() {
        resetScoreNames = true;
    }

    private void handleResetScoreNames() {
        if (!resetScoreNames) return;

        convertFormHandler.clearScoreNameFrom();
        convertFormHandler.setScoreNameFrom();
    }

    public void showPassword() {
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

    public void showSchemaFileLocation() {
        boolean showSchemaLocation = checkboxOn("generateSchema");
        Node chooseSchemaLocationLink = getNode("chooseSchemaLocationLink");
        chooseSchemaLocationLink.setVisible(showSchemaLocation);
        Node schemaFileLocation = getNode("schemaFileLocation");
        schemaFileLocation.setVisible(showSchemaLocation);
    }

    public void setOpenFileLocationInTextField(String textFieldName) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(getStage());
        if (file == null) return;

        TextField textField = getTextField(textFieldName);
        textField.setText(file.getAbsolutePath());
    }

    public void setChooseFileLocationInTextField(String textFieldName, String extensionLabel, String extensionFilter) {
        FileChooser fileChooser = new FileChooser();
        if (StringUtil.isNotEmpty(extensionFilter)) {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(extensionLabel, extensionFilter);
            fileChooser.getExtensionFilters().add(extFilter);
        }
        File file = fileChooser.showSaveDialog(getStage());
        if(file != null){
            TextField schemaFileLocation = getTextField(textFieldName);
            schemaFileLocation.setText(file.getAbsolutePath());
        }
    }

    public void initializeConvertForm() {
        convertFormHandler.initializeForm();
    }

    public void handleDisplayUpdates() {
        handleResetScoreNames();
        handlePdfReaderDisplay();
    }

    public void handlePdfReaderDisplay() {
        boolean pdfReaderSet = StringUtil.isNotEmpty(AppProperties.getOptionalProperty("location.pdfreader"));
        getNode("openPdf").setVisible(pdfReaderSet);
        getNode("openPdfLabel1").setVisible(!pdfReaderSet);
        getNode("openPdfLabel2").setVisible(!pdfReaderSet);
        getNode("openPdfLabel3").setVisible(!pdfReaderSet);
    }
}
