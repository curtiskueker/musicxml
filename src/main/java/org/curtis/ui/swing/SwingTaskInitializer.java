package org.curtis.ui.swing;

import org.curtis.ui.task.TaskInitializer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class SwingTaskInitializer implements TaskInitializer {
    private Map<String, Component> componentMap;

    public SwingTaskInitializer(Map<String, Component> componentMap) {
        this.componentMap = componentMap;
    }

    public String getText(String componentName) {
        Component component = componentMap.get(componentName);
        if (component == null) return null;
        if (!(component instanceof JTextField)) return null;

        JTextField textField = (JTextField)component;
        return textField.getText();
    }

    public boolean isSelected(String componentName) {
        Component component = componentMap.get(componentName);
        if (component == null) return false;
        if (!(component instanceof JCheckBox)) return false;

        JCheckBox checkBox = (JCheckBox)component;
        return checkBox.isSelected();
    }

    public String getDirectoryLocation(String componentName) {
        Component component = componentMap.get(componentName);
        if (component == null) return "";
        if ((!(component instanceof JFileChooser))) return "";

        JFileChooser fileChooser = (JFileChooser)component;
        File directory = fileChooser.getSelectedFile();
        return directory == null ? "" : directory.getAbsolutePath();
    }

    public String getSelection(String componentName) {
        Component component = componentMap.get(componentName);
        if (component == null) return "";
        if (!(component instanceof JComboBox)) return "";

        JComboBox selection = (JComboBox)component;
        return (String)selection.getSelectedItem();
    }
}
