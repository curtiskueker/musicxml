package org.curtis.ui.javafx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.curtis.ui.javafx.output.StatusOutput;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TasksController {
    private Scene scene;
    private Stage stage;
    private StatusOutput statusOutput;
    private static final List<String> fromBoxes = new ArrayList<>(Arrays.asList("musicXmlFromBox", "dbFromBox", "lyFromBox"));
    private static final List<String> toBoxes = new ArrayList<>(Arrays.asList("musicXmlToBox", "dbToBox", "lyToBox", "pdfToBox"));

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public StatusOutput getStatusOutput() {
        return statusOutput;
    }

    public void setStatusOutput(StatusOutput statusOutput) {
        this.statusOutput = statusOutput;
    }

    public Node getNode(String nodeName) {
        return getScene().lookup("#" + nodeName);
    }

    @FXML
    private void saveSettings() {
        buttonPressed("Database settings saved");
    }

    @FXML
    private void showPassword() {

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
            TextField schemaFileLocation = (TextField)getNode("schemaFileLocation");
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
        buttonPressed("Convert action executed");
    }

    @FXML
    private void executeTables() {
        buttonPressed("Database action executed");
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
    private void executeLyPdf() {
        buttonPressed("Lilypond/PDF action executed");
    }

    @FXML
    private void setValidateLocation() {
        setFileLocationInTextField("validateLocation");
    }

    @FXML
    private void executeValidate() {
        buttonPressed("Validate action executed");
    }

    private void setFileLocationInTextField(String textFieldName) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(getStage());
        if (file == null) return;

        TextField textField = (TextField)getNode(textFieldName);
        textField.setText(file.getAbsolutePath());
    }

    private boolean checkboxOn(String controlName) {
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

    private void buttonPressed(String text) {
        getStatusOutput().clear();

        // Run task in thread

        System.err.println(text);
    }
}
