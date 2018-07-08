package org.curtis.lilypond.musicdata;

import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Dashes;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsMarking;
import org.curtis.musicxml.direction.directiontype.OtherDirection;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;

public class DirectiontypeBuilder extends MusicDataBuilder {
    public DirectiontypeBuilder() {

    }

    public StringBuilder buildRehearsal(Rehearsal rehearsal) {
        append("\\mark ");
        formattedTextBuild(rehearsal.getFormattedText());

        return stringBuilder;
    }

    public StringBuilder buildSegno(Segno segno) {
        append("\\segno");

        return stringBuilder;
    }

    public StringBuilder buildWords(Words words) {
        if (words.isTextMark()) append("\\mark ");
        formattedTextBuild(words.getFormattedText());

        return stringBuilder;
    }

    public StringBuilder buildCoda(Coda coda) {
        appendLine("\\coda");

        return stringBuilder;
    }

    public StringBuilder buildWedge(Wedge wedge) {
        WedgeType wedgeType = wedge.getType();
        switch (wedgeType) {
            case CRESCENDO:
                append("\\<");
                break;
            case DIMINUENDO:
                append("\\>");
                break;
            case STOP:
                append("\\!");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildDynamics(Dynamics dynamics) {
        List<DynamicsMarking> dynamicsMarkings = dynamics.getMarkings();
        for(DynamicsMarking dynamicsMarking : dynamicsMarkings) {
            switch (dynamicsMarking.getDynamicsType()) {
                case P:
                    append("\\p");
                    break;
                case PP:
                    append("\\pp");
                    break;
                case F:
                    append("\\f");
                    break;
                case FF:
                    append("\\ff");
                    break;
                case MP:
                    append("\\mp");
                    break;
                case MF:
                    append("\\mf");
                    break;
                case SF:
                    append("\\sf");
                    break;
                case FP:
                    append("\\fp");
                    break;
                case FZ:
                    append("\\fz");
                    break;
            }
        }

        return stringBuilder;
    }

    public StringBuilder buildDashes(Dashes dashes) {
        switch (dashes.getType()) {
            case START:
                append("\\startTextSpan ");
                break;
            case STOP:
                append("\\stopTextSpan ");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildOtherDirection(OtherDirection otherDirection) {
        System.err.println("Warning: OrnamentBuilder.buildOtherDirection not implemented");

        if (!otherDirection.getPrintObject()) return stringBuilder;

        return stringBuilder;
    }

    private void formattedTextBuild(FormattedText formattedText) {
        if (formattedText == null) return;

        String value = formattedText.getValue();
        if (StringUtil.isEmpty(value)) return;

        value = value.replaceAll("[^\\x00-\\x7F]", "");

        TextFormatting textFormatting = formattedText.getTextFormatting();
        PrintStyleAlign printStyleAlign = textFormatting.getPrintStyleAlign();
        PrintStyle printStyle = printStyleAlign.getPrintStyle();
        Font font = printStyle.getFont();

        append("\\markup { ");

        if(font.getFontStyle() != null) {
            switch (font.getFontStyle()) {
                case ITALIC:
                    append("\\italic ");
                    break;
            }
        }
        if(font.getFontWeight() != null) {
            switch (font.getFontWeight()) {
                case BOLD:
                    append("\\bold ");
                    break;
            }
        }
        if (font.getFontSize() != null) {
            BigDecimal fontSize = font.getFontSize().getFontSize();
            if (MathUtil.isPositive(fontSize)) {
                append("\\abs-fontsize #");
                append(String.valueOf(fontSize.intValue()));
                append(" ");
            }
        }

        append("\"");
        append(value);
        append("\"");

        append(" }");
    }
}
