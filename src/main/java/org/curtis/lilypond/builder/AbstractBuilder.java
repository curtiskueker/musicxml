package org.curtis.lilypond.builder;

public abstract class AbstractBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();
    public abstract StringBuilder build();

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
}
