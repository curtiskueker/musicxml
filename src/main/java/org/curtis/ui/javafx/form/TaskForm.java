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
        CheckBox checkBox = (CheckBox)getNode(controlName);

        return checkBox.isSelected();
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

    public void handlePdfReaderDisplay() {
        if (StringUtil.isEmpty(AppProperties.getOptionalProperty("location.pdfreader"))) {
            getNode("openPdf").setVisible(false);
            getNode("openPdfLabel1").setVisible(true);
            getNode("openPdfLabel2").setVisible(true);
            getNode("openPdfLabel3").setVisible(true);
        } else {
            getNode("openPdf").setVisible(true);
            getNode("openPdfLabel1").setVisible(false);
            getNode("openPdfLabel2").setVisible(false);
            getNode("openPdfLabel3").setVisible(false);
        }
    }
}
