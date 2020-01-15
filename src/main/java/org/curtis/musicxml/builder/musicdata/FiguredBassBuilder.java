package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.note.Figure;
import org.curtis.musicxml.note.FiguredBass;

public class FiguredBassBuilder extends MusicDataBuilder {
    private FiguredBass figuredBass;

    public FiguredBassBuilder(FiguredBass figuredBass) {
        this.figuredBass = figuredBass;
    }

    public StringBuilder build() {
        if (figuredBass == null) return stringBuilder;

        buildOpenElement("figured-bass");
        buildAttributes(FormattingBuilder.buildPrintStyle(figuredBass.getPrintStyle()));
        buildAttributes(FormattingBuilder.buildPrintout(figuredBass.getPrintout()));
        buildAttribute("parentheses",  figuredBass.getParentheses());
        buildCloseElement();
        for (Figure figure : figuredBass.getFigures()) {
            buildStartElement("figure");
            buildStyleText("prefix", figure.getPrefix());
            buildStyleText("figure-number", figure.getFigureNumber());
            buildStyleText("suffix", figure.getSuffix());
            buildExtend(figure.getExtend());
            buildEditorial(figure.getEditorial());
            buildEndElement("figure");
        }
        buildElementWithValue("duration", figuredBass.getDuration());
        buildEditorial(figuredBass.getEditorial());
        buildEndElement("figured-bass");

        return stringBuilder;
    }
}
