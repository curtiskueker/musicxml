package org.curtis.ui.javafx.initialize;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import org.curtis.ui.javafx.TasksController;
import org.curtis.ui.task.TaskInitializer;

import java.util.HashMap;
import java.util.Map;

public abstract class JavafxTaskInitializer implements TaskInitializer {
    protected Map<String, Node> nodeMap = new HashMap<>();
    protected TasksController tasksController;

    public JavafxTaskInitializer(TasksController tasksController) {
        this.tasksController = tasksController;
    }

    public String getText(String nodeName) {
        Node node = nodeMap.get(nodeName);
        if (node == null) return null;
        if (!(node instanceof TextInputControl)) return null;

        TextInputControl textField = (TextInputControl)node;
        return textField.getText();
    }

    public boolean isSelected(String nodeName) {
        Node node = nodeMap.get(nodeName);
        if (node == null) return false;
        if (!(node instanceof CheckBox)) return false;

        CheckBox checkBox = (CheckBox)node;
        return checkBox.isSelected();
    }

    public String getDirectoryLocation(String nodeName) {
        return getText(nodeName);
    }

    public String getSelection(String nodeName) {
        Node node = nodeMap.get(nodeName);
        if (node == null) return "";
        if (!(node instanceof ComboBox)) return "";

        ComboBox selection = (ComboBox)node;
        return (String)selection.getValue();
    }

    public abstract void initializeNodeMap();
}
