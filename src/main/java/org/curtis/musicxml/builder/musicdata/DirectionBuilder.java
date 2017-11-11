package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.util.BuildUtil;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.type.DirectionType;
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
                append(BuildUtil.getPlacement(direction.getPlacement()));
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
                /*
                Wedge wedge = (Wedge)directionType;
                WedgeType wedgeType = wedge.getType();
                switch (wedgeType) {
                    case CRESCENDO:
                        append(BuildUtil.getPlacement(direction.getPlacement()));
                        append("\\<");
                        break;
                    case DIMINUENDO:
                        append(BuildUtil.getPlacement(direction.getPlacement()));
                        append("\\>");
                        break;
                    case STOP:
                        append("\\!");
                        break;
                }
                */
            }
        }

        return stringBuilder;
    }
}
