package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.part.PartBuilder;
import org.curtis.musicxml.score.Measure;
import org.curtis.util.StringUtil;

public abstract class AbstractBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();

    public abstract StringBuilder build() throws BuildException;

    protected void appendLine(String string) {
        stringBuilder.append(string);
        stringBuilder.append("\n");
    }

    protected void appendLine() {
        appendLine("");
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }

    public void clear() {
        stringBuilder = new StringBuilder();
    }

    protected String getPartAndMeasure(Measure measure) {
        String value = "";
        if (StringUtil.isNotEmpty(PartBuilder.CURRENT_PART_ID)) {
            value += "Part " + PartBuilder.CURRENT_PART_ID;
        }
        if (measure != null) {
            value += " Measure " + measure.getNumber() + ": ";
        }

        return value;
    }

    protected void displayMeasureMessage(Measure measure, String message) {
        System.err.println(getPartAndMeasure(measure) + message);
    }
}
