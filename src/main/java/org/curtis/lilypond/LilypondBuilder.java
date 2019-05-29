package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;

import java.math.BigDecimal;

public abstract class LilypondBuilder extends BaseBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();
    private static final String TAB = "    ";

    private static Integer CURRENT_TAB_COUNT = 0;

    public abstract StringBuilder build() throws BuildException;

    protected void appendLine(String string) {
        stringBuilder.append(string);
        stringBuilder.append("\n");
        for (int i = 0; i < CURRENT_TAB_COUNT; i++) stringBuilder.append(TAB);
    }

    protected void appendLine() {
        appendLine("");
    }

    protected void appendLine(BigDecimal value) {
        if (value == null) return;

        appendLine(value.toString());
    }

    protected void appendStartSection(String string) {
        CURRENT_TAB_COUNT++;
        appendLine(string);
    }

    protected void appendEndSection(String string) {
        CURRENT_TAB_COUNT--;
        int lastTabIndex = stringBuilder.lastIndexOf(TAB);
        stringBuilder.delete(lastTabIndex, lastTabIndex + TAB.length());
        appendLine(string);
    }

    protected void appendEndSection() {
        appendEndSection("");
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }

    protected void append(Integer value) {
        append(String.valueOf(value));
    }

    protected void clear() {
        stringBuilder = new StringBuilder();
    }
}
