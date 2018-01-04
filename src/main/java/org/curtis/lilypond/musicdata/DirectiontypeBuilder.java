package org.curtis.lilypond.musicdata;

import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsType;
import org.curtis.musicxml.direction.directiontype.Wedge;
import org.curtis.musicxml.direction.directiontype.WedgeType;
import org.curtis.musicxml.direction.directiontype.Words;

import java.util.List;

public class DirectiontypeBuilder extends MusicDataBuilder {
    public DirectiontypeBuilder() {

    }

    public StringBuilder buildWords(Words words) {
        if (words.isTextMark()) append("\\mark ");
        append("\\markup { ");

        FormattedText formattedText = words.getFormattedText();
        TextFormatting textFormatting = formattedText.getTextFormatting();
        PrintStyleAlign printStyleAlign = textFormatting.getPrintStyleAlign();
        PrintStyle printStyle = printStyleAlign.getPrintStyle();
        Font font = printStyle.getFont();
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

        append(formattedText.getValue());

        append(" }");

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
        List<DynamicsType> dynamicsTypes = dynamics.getTypes();
        for(DynamicsType dynamicsType : dynamicsTypes) {
            switch (dynamicsType) {
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
}
