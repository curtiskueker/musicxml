package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.note.Figure;
import org.curtis.musicxml.note.FigurePart;
import org.curtis.musicxml.note.FiguredBass;

public class FiguredBassBuilder extends MusicDataBuilder {
    private FiguredBass figuredBass;

    public FiguredBassBuilder(FiguredBass figuredBass) {
        this.figuredBass = figuredBass;
    }

    public StringBuilder build() {
        if (figuredBass == null) return stringBuilder;

        buildOpenElement("figured-bass");
        buildAttribute("id", figuredBass.getElementId());
        buildAttributes(DisplayBuilder.buildDisplay(figuredBass.getDisplay()));
        buildAttributes(FormattingBuilder.buildPrintout(figuredBass.getPrintout()));
        buildAttribute("parentheses",  figuredBass.getParentheses());
        buildCloseElement();
        for (Figure figure : figuredBass.getFigures()) {
            buildStartElement("figure");
            buildFigurePart("prefix", figure.getPrefix());
            buildFigurePart("figure-number", figure.getFigureNumber());
            buildFigurePart("suffix", figure.getSuffix());
            buildExtend(figure.getExtend());
            buildEditorial(figure.getEditorial());
            buildEndElement("figure");
        }
        buildElementWithValue("duration", figuredBass.getDuration());
        buildEditorial(figuredBass.getEditorial());
        buildEndElement("figured-bass");

        return stringBuilder;
    }

    private void buildFigurePart(String elementName, FigurePart figurePart) {
        if (figurePart == null) return;

        buildElementWithValueAndAttributes(elementName, figurePart.getValue(), DisplayBuilder.buildDisplay(figurePart.getDisplay()));
    }
}
