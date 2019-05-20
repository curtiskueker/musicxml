package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;

public abstract class LilypondBuilder extends BaseBuilder {
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

    protected void append(Integer value) {
        append(String.valueOf(value));
    }

    protected void clear() {
        stringBuilder = new StringBuilder();
    }

}
