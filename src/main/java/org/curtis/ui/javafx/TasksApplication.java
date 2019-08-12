package org.curtis.ui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
