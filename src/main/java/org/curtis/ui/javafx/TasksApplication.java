package org.curtis.ui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.curtis.ui.javafx.output.StatusOutput;

import java.io.PrintStream;
import java.util.Objects;

public class TasksApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("javafx/musicxmltasks.fxml")));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("MusicXml Tasks");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        TasksController tasksController = fxmlLoader.getController();
        tasksController.setScene(scene);
        tasksController.setStage(primaryStage);

        primaryStage.show();

        // Setup status output box
        TextArea statusTextArea = (TextArea)tasksController.getNode("statusTextArea");
        StatusOutput statusOutput = new StatusOutput(statusTextArea);
        PrintStream statusPrintStream = new PrintStream(statusOutput);
        System.setErr(statusPrintStream);
        System.setOut(statusPrintStream);
        tasksController.setStatusOutput(statusOutput);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
