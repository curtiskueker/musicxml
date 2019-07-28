package org.curtis.ui.task;

import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.exception.TaskException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public abstract class MusicXmlTask {
    static {
        MusicXmlUtil.DEBUG = true;
    }

    protected Map<String, Component> componentMap;

    protected MusicXmlTask(Map<String, Component> componentMap) {
        this.componentMap = componentMap;
    }

    public abstract void execute() throws TaskException;

    protected String getText(Component component) {
        if (component == null) return null;
        if (!(component instanceof JTextField)) return null;

        JTextField textField = (JTextField)component;
        return textField.getText();
    }

    protected boolean isSelected(Component component) {
        if (component == null) return false;
        if (!(component instanceof JCheckBox)) return false;

        JCheckBox checkBox = (JCheckBox)component;
        return checkBox.isSelected();
    }

    protected String getDirectoryLocation(Component component) {
        if (component == null) return "";
        if ((!(component instanceof JFileChooser))) return "";

        JFileChooser fileChooser = (JFileChooser)component;
        File directory = fileChooser.getSelectedFile();
        return directory == null ? "" : directory.getAbsolutePath();
    }

    protected String getSelection(Component component) {
        if (component == null) return "";
        if (!(component instanceof JComboBox)) return "";

        JComboBox selection = (JComboBox)component;
        return (String)selection.getSelectedItem();
    }
}
