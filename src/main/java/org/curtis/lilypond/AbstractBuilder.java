package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.score.Measure;
import org.curtis.util.StringUtil;

public abstract class AbstractBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();

    public abstract StringBuilder build() throws BuildException;

    protected void appendLine(String string) {
        stringBuilder.append(string);
        stringBuilder.append("\n");
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }

    public void clear() {
        stringBuilder = new StringBuilder();
    }

    public String getExceptionStringPrefix(Measure measure) {
        String exceptionStringPrefix = "";
        if (StringUtil.isNotEmpty(PartBuilder.CURRENT_PART_ID)) {
            exceptionStringPrefix += "Part " + PartBuilder.CURRENT_PART_ID;
        }
        if (measure != null) {
            exceptionStringPrefix += " Measure " + measure.getNumber() + ": ";
        }

        return exceptionStringPrefix;
    }
}
