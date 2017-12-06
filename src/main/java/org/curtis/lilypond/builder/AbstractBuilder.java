package org.curtis.lilypond.builder;

import org.curtis.musicxml.attributes.TimeSignature;

public abstract class AbstractBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();
    protected TimeSignature currentTimeSignature;

    public void setValues(TimeSignature timeSignature) {
        currentTimeSignature = timeSignature;
    }

    public abstract StringBuilder build();

    protected void appendLine(String string) {
        stringBuilder.append(string);
        stringBuilder.append("\n");
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }

    public TimeSignature getCurrentTimeSignature() {
        return currentTimeSignature;
    }

    public void setCurrentTimeSignature(TimeSignature currentTimeSignature) {
        this.currentTimeSignature = currentTimeSignature;
    }

    public void clear() {
        stringBuilder = new StringBuilder();
    }
}
