package org.curtis.lilypond.builder.musicdata;

import org.curtis.lilypond.builder.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.type.DirectionType;
import org.curtis.musicxml.direction.type.Dynamics;
import org.curtis.musicxml.direction.type.DynamicsType;
import org.curtis.musicxml.direction.type.Wedge;
import org.curtis.musicxml.direction.type.WedgeType;
import org.curtis.musicxml.direction.type.Words;

import java.util.List;

public class DirectionBuilder extends MusicDataBuilder {
    private Direction direction;

    public DirectionBuilder(Direction direction) {
        super(direction);
        this.direction = direction;
    }

    public StringBuilder build() {
        List<DirectionType> directionTypes = direction.getDirectionTypes();
        for(DirectionType directionType : directionTypes) {
            if(directionType instanceof Words) {
                append(PlacementBuildUtil.getPlacement(direction.getPlacement()));
                Words words = (Words)directionType;
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
            } else if(directionType instanceof Wedge) {
                Wedge wedge = (Wedge)directionType;
                WedgeType wedgeType = wedge.getType();
                switch (wedgeType) {
                    case CRESCENDO:
                        append(PlacementBuildUtil.getPlacement(direction.getPlacement()));
                        append("\\<");
                        break;
                    case DIMINUENDO:
                        append(PlacementBuildUtil.getPlacement(direction.getPlacement()));
                        append("\\>");
                        break;
                    case STOP:
                        append("\\!");
                        break;
                }
            } else if(directionType instanceof Dynamics) {
                append(PlacementBuildUtil.getPlacement(direction.getPlacement()));
                Dynamics dynamics = (Dynamics)directionType;
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
            }
        }

        return stringBuilder;
    }
}
