package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Dashes;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsMarking;
import org.curtis.musicxml.direction.directiontype.OctaveShift;
import org.curtis.musicxml.direction.directiontype.OctaveShiftType;
import org.curtis.musicxml.direction.directiontype.OtherDirection;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Font;
import org.curtis.musicxml.display.TextFormat;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.List;

public class DirectiontypeBuilder extends MusicDataBuilder {
    public DirectiontypeBuilder() {

    }

    public StringBuilder buildRehearsal(Rehearsal rehearsal) {
        append("\\mark ");
        formattedDisplayBuild(rehearsal.getDisplay(), rehearsal.getTextFormat());

        return stringBuilder;
    }

    public StringBuilder buildSegno(Segno segno) {
        append("\\segno");

        return stringBuilder;
    }

    public StringBuilder buildWords(Words words) {
        if (words.isTextMark()) append("\\mark ");
        formattedDisplayBuild(words.getDisplay(), words.getTextFormat());

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
            case INVALID:
                System.err.println("Wedge not handled");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildDynamics(Dynamics dynamics) {
        List<DynamicsMarking> dynamicsMarkings = dynamics.getMarkings();
        if (dynamicsMarkings.isEmpty()) return stringBuilder;

        StringBuilder dynamicsBuilder = new StringBuilder();
        for(DynamicsMarking dynamicsMarking : dynamicsMarkings) {
            String markingValue = BuilderUtil.enumValue(dynamicsMarking.getDynamicsType());
            if (markingValue.equals("other-dynamics")) dynamicsBuilder.append(dynamicsMarking.getValue());
            else dynamicsBuilder.append(markingValue);
        }

        if (dynamicsBuilder.length() == 0) return stringBuilder;

        append("\\");
        append(dynamicsBuilder.toString());

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

    public StringBuilder buildOctaveShift(OctaveShift octaveShift) {
        Integer size = octaveShift.getSize();
        if (size != 8 && size != 15) {
            System.err.println("Warning: octave shift size must be either 8 or 15");
            return stringBuilder;
        }

        OctaveShiftType octaveShiftType = octaveShift.getType();
        append(" \\ottava #");
        if (octaveShiftType == OctaveShiftType.UP) append("-");

        if (octaveShiftType == OctaveShiftType.STOP) append("0");
        else if (size == 15) append("2");
        else append("1");

        return stringBuilder;
    }

    public StringBuilder buildOtherDirection(OtherDirection otherDirection) {
        System.err.println("Warning: other-direction element handling not implemented");

        if (!TypeUtil.getBooleanDefaultYes(otherDirection.getPrintObject())) return stringBuilder;

        return stringBuilder;
    }

    private void formattedDisplayBuild(Display display, TextFormat textFormat) {
        if (textFormat == null) return;

        String value = textFormat.getValue();
        if (StringUtil.isEmpty(value)) return;

        value = value.replaceAll("[^\\x00-\\x7F]", "");

        Font font = null;
        if (display != null) {
            font = display.getFont();
            if (font != null) {
                append("\\markup { ");

                if (font.getFontStyle() != null) {
                    switch (font.getFontStyle()) {
                        case ITALIC:
                            append("\\italic ");
                            break;
                    }
                }
                if (font.getFontWeight() != null) {
                    switch (font.getFontWeight()) {
                        case BOLD:
                            append("\\bold ");
                            break;
                    }
                }
                if (font.getNonNumericFontSize() != null) {
                    BigDecimal fontSize = font.getNumericFontSize();
                    if (MathUtil.isPositive(fontSize)) {
                        append("\\abs-fontsize #");
                        append(MathUtil.truncate(fontSize));
                        append(" ");
                    }
                }
            }
        }

        append("\"");
        append(value);
        append("\"");

        if (font != null) append(" }");
    }
}
