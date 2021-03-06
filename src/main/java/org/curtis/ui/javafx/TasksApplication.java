package org.curtis.ui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.task.TaskConstants;

import java.util.Objects;

public class TasksApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        PropertiesHandler.addLocalProperties();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("javafx/musicxmltasks.fxml")));
        Parent root = loader.load();
        primaryStage.setTitle(TaskConstants.TASKS_TITLE);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnHidden(e -> {((TasksController)loader.getController()).cleanup();});
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
