package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.note.Figure;
import org.curtis.musicxml.note.FiguredBass;

public class FiguredBassBuilder extends BaseBuilder {
    private FiguredBass figuredBass;

    public FiguredBassBuilder(FiguredBass figuredBass) {
        this.figuredBass = figuredBass;
    }

    public StringBuilder build() {
        if (figuredBass == null) return stringBuilder;

        appendLine("<figured-bass>");
        for (Figure figure : figuredBass.getFigures()) {
            appendLine("<figure>");
            NoteBuilder noteBuilder = new NoteBuilder();
            noteBuilder.buildExtend(figure.getExtend());
            append(noteBuilder.build().toString());
            appendLine("</figure>");
        }
        appendLine("</figured-bass>");

        return stringBuilder;
    }
}
