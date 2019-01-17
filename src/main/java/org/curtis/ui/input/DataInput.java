package org.curtis.ui.input;

import java.util.ArrayList;
import java.util.List;

public abstract class DataInput {
    private String title;
    private List<InputRow> inputRows = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InputRow> getInputRows() {
        return inputRows;
    }

    public void setInputRows(List<InputRow> inputRows) {
        this.inputRows = inputRows;
    }
}
