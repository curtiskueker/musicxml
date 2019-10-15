package org.curtis.ui.javafx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.curtis.properties.AppProperties;
import org.curtis.ui.javafx.form.ConvertFormHandler;
import org.curtis.util.StringUtil;

import java.io.File;
import java.io.PrintStream;

public class TasksController {
    private StatusOutput statusOutput;
    private ConvertFormHandler convertFormHandler = new ConvertFormHandler(this);
    private Thread outputThread;
    private boolean outputTest = false;

    @FXML
    private VBox taskBox;

    @FXML
    private TextArea statusTextArea;

    @FXML
    public void initialize() {
        // Setup status output box
        statusOutput = new StatusOutput(statusTextArea);
        PrintStream statusPrintStream = new PrintStream(statusOutput);
        if (!outputTest) {
            System.setErr(statusPrintStream);
            System.setOut(statusPrintStream);
        }

        Runnable outputRunnable = statusOutput::handle;
        outputThread = new Thread(outputRunnable);
        outputThread.start();
    }

    public void cleanup() {
        if (outputThread != null) outputThread.interrupt();
    }

    public Scene getScene() {
        if (taskBox == null) return null;

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
        if (getScene() == null) return;

        Tab tab = (Tab)actionEvent.getSource();
        if (!tab.isSelected()) return;

        TaskExecutor taskExecutor = new TaskExecutor(tab.getId(), this);
        taskExecutor.initializeForm();
    }

    @FXML
    private void fromListSelected(ActionEvent actionEvent) {
        ComboBox comboBox = (ComboBox)actionEvent.getSource();
        convertFormHandler.fromListSelected((String)comboBox.getValue());
    }

    @FXML
    private void toListSelected(ActionEvent actionEvent) {
        ComboBox comboBox = (ComboBox)actionEvent.getSource();
        convertFormHandler.toListSelected((String)comboBox.getValue());
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
        setChooseFileLocationInTextField("schemaFileLocation", "SQL Files (*.sql)", "*.sql");
    }

    @FXML
    private void setConvertFromList() {
        ComboBox convertFromList = (ComboBox)getNode("convertFromList");
        if (!convertFromList.getItems().isEmpty()) return;

        convertFormHandler.initializeForm();
    }

    @FXML
    private void chooseMusicXmlFromFile() {
        setOpenFileLocationInTextField("musicXmlFromFile");
    }

    @FXML
    private void chooseLyFromFile() {
        setOpenFileLocationInTextField("lyFromFile");
    }

    @FXML
    private void chooseMusicXmlToFile() {
        setChooseFileLocationInTextField("musicXmlToFile", "XML Files (*.xml)", "*.xml");
    }

    @FXML
    private void chooseLyToFile() {
        setChooseFileLocationInTextField("lyToFile", "Lilypond Files (*.ly)", "*.ly");
    }

    @FXML
    private void choosePdfToFile() {
        setChooseFileLocationInTextField("pdfToFile", "No extension", "");
    }

    @FXML
    private void setLilypondLocation() {
        setOpenFileLocationInTextField("lilypondLocation");
    }

    @FXML
    private void setPdfLocation() {
        setOpenFileLocationInTextField("pdfLocation");
    }

    @FXML
    private void setValidateLocation() {
        setOpenFileLocationInTextField("validateLocation");
    }

    public String getFromSelection() {
        ComboBox fromList = (ComboBox)getNode("convertFromList");
        return  (String)fromList.getValue();
    }

    public String getToSelection() {
        ComboBox toList = (ComboBox)getNode("convertToList");
        return  (String)toList.getValue();
    }

    private void setOpenFileLocationInTextField(String textFieldName) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(getStage());
        if (file == null) return;

        TextField textField = getTextField(textFieldName);
        textField.setText(file.getAbsolutePath());
    }

    private void setChooseFileLocationInTextField(String textFieldName, String extensionLabel, String extensionFilter) {
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

    public boolean checkboxOn(String controlName) {
        CheckBox checkBox = (CheckBox)getNode(controlName);

        return checkBox.isSelected();
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
