package org.curtis.ui.task;

public interface TaskInitializer {
    String getText(String componentName);
    boolean isSelected(String componentName);
    String getDirectoryLocation(String componentName);
    String getSelection(String componentName);
}
