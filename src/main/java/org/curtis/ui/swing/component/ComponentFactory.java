package org.curtis.ui.swing.component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

public class ComponentFactory {
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private ComponentFactory() {

    }

    public static JPanel newPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(getBackgroundColor());
        panel.setLayout(new GridBagLayout());

        return panel;
    }

    public static JLabel newLabel(String text) {
        JLabel label = new JLabel();
        label.setBackground(getBackgroundColor());
        label.setText(text);

        return label;
    }

    public static JLabel newBoldLabel(String text, int size) {
        JLabel label = newLabel(text);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, size));

        return label;
    }

    public static JTextField newTextField(String text) {
        JTextField textField = new JTextField();
        textField.setBackground(getBackgroundColor());
        textField.setText(text);

        return textField;
    }

    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }
}
