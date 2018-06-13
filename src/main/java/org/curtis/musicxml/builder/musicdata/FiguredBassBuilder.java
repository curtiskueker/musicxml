package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.Figure;
import org.curtis.musicxml.note.FiguredBass;

public class FiguredBassBuilder extends BaseBuilder {
    private FiguredBass figuredBass;

    public FiguredBassBuilder(FiguredBass figuredBass) {
        this.figuredBass = figuredBass;
    }

    public StringBuilder build() {
        if (figuredBass == null) return stringBuilder;

        append("<figured-bass");
        FormattingBuilder.buildPrintStyle(figuredBass.getPrintStyle()).forEach((k, v) -> buildAttribute(k, v));
        FormattingBuilder.buildPrintout(figuredBass.getPrintout()).forEach((k, v) -> buildAttribute(k, v));
        buildAttribute("parentheses", BuilderUtil.yesOrNo(figuredBass.getParentheses()));
        appendLine(">");
        for (Figure figure : figuredBass.getFigures()) {
            appendLine("<figure>");
            buildStyleText("prefix", figure.getPrefix());
            buildStyleText("figure-number", figure.getFigureNumber());
            buildStyleText("suffix", figure.getSuffix());
            buildExtend(figure.getExtend());
            appendLine("</figure>");
        }
        buildElementWithValue("duration", BuilderUtil.stringValue(figuredBass.getDuration()));
        buildEditorial(figuredBass.getEditorial());
        appendLine("</figured-bass>");

        return stringBuilder;
    }
}
