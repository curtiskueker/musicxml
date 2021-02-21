package org.curtis.ui.javafx.initialize;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputControl;
import org.curtis.ui.javafx.form.TaskForm;
import org.curtis.ui.task.TaskInitializer;

import java.util.HashMap;
import java.util.Map;

public abstract class JavafxTaskInitializer implements TaskInitializer {
    protected Map<String, Node> nodeMap = new HashMap<>();
    protected TaskForm taskForm;

    public JavafxTaskInitializer(TaskForm taskForm) {
        this.taskForm = taskForm;
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

        if (node instanceof CheckBox) return ((CheckBox)node).isSelected();
        else if (node instanceof RadioButton) return ((RadioButton)node).isSelected();
        else return false;
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
