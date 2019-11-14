package org.curtis.ui.swing.component;

import org.curtis.ui.task.TaskConstants;
import org.curtis.util.StringUtil;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.List;

public class ComponentFactory {
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 242);

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

    public static JTextField newTextField(String text, int size) {
        JTextField textField = new JTextField();
        textField.setBackground(getBackgroundColor());
        textField.setText(text);
        textField.setPreferredSize(new Dimension(size, 25));

        return textField;
    }

    public static JPasswordField newPasswordField(String text) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(getBackgroundColor());
        passwordField.setText(text);

        return passwordField;
    }

    public static JCheckBox newCheckBox() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBackground(getBackgroundColor());

        return checkBox;
    }

    public static JCheckBox newCheckBox(String text, int position) {
        JCheckBox checkBox = newCheckBox();
        checkBox.setText(text);
        checkBox.setHorizontalTextPosition(position);

        return checkBox;
    }

    public static JFileChooser newFileChooser(String extensionFilter, String fileName) {
        JFileChooser fileChooser = newFileChooser();
        if (StringUtil.isNotEmpty(extensionFilter)) {
            FileFilter fileFilter = new FileNameExtensionFilter(extensionFilter, extensionFilter);
            fileChooser.addChoosableFileFilter(fileFilter);
        }
        if (StringUtil.isNotEmpty(fileName))
            fileChooser.setSelectedFile(new File(fileName));

        return fileChooser;
    }

    public static JFileChooser newDirectoryChooser() {
        JFileChooser fileChooser = newFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        return fileChooser;
    }

    private static JFileChooser newFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setBackground(getBackgroundColor());
        fileChooser.setPreferredSize(new Dimension(450, 200));
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        fileChooser.setControlButtonsAreShown(false);
        fileChooser.setAcceptAllFileFilterUsed(false);

        return fileChooser;
    }

    public static JComboBox<String> newComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBackground(ComponentFactory.getBackgroundColor());
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("");
        comboBox.setModel(model);

        return comboBox;
    }

    public static JComboBox<String> newComboBox(List<String> itemList) {
        JComboBox<String> comboBox = newComboBox();
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        model.addAll(itemList);

        return comboBox;
    }

    public static JComboBox<String> newComboBox(List<String> itemList, String selectedItem) {
        JComboBox<String> comboBox = newComboBox(itemList);
        comboBox.setSelectedItem(selectedItem);

        return comboBox;
    }

    public static JButton newButton(String text) {
        JButton button = new JButton();
        button.setBackground(getBackgroundColor());
        button.setText(text);

        return button;
    }

    public static JMenuBar newMenu(String title, List<String> menuItemLabels) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(getBackgroundColor());

        JMenu menu = new JMenu(title);
        menu.setBackground(getBackgroundColor());
        for (String menuItemLabel : menuItemLabels) {
            if (menuItemLabel.equals(TaskConstants.MENU_SEPARATOR)) menu.addSeparator();
            else {
                JMenuItem menuItem = new JMenuItem(menuItemLabel);
                menuItem.setBackground(getBackgroundColor());
                menu.add(menuItem);
            }
        }
        menuBar.add(menu);

        return menuBar;
    }

    public static JScrollPane newScrollPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(getBackgroundColor());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }
}
