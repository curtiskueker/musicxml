package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.barline.RepeatDirection;

public class BarlineBuilder extends MusicDataBuilder {
    public StringBuilder buildBarline(Barline barline) throws BuildException {
        BarStyleColor barStyleColor = barline.getBarStyle();
        if(barStyleColor == null) return stringBuilder;

        Repeat repeat = barline.getRepeat();
        RepeatDirection repeatDirection = repeat == null ? null : repeat.getDirection();

        append(" \\bar \"");

       if(repeatDirection == RepeatDirection.BACKWARD) {
            append(":");
        }

        // bar style
        BarStyle barStyle = barStyleColor.getBarStyle();
        if(barStyle != null) {
            switch (barStyle) {
                case DOTTED:
                    append(";");
                    break;
                case DASHED:
                    append("!");
                    break;
                case LIGHT_LIGHT:
                    append("||");
                    break;
                case LIGHT_HEAVY:
                    append("|.");
                    break;
                case HEAVY_LIGHT:
                    append(".|");
                    break;
                case HEAVY_HEAVY:
                    append("..");
                default:
                    throw new BuildException("Unimplemented bar style: " + barStyle);
            }
        }

        if(repeatDirection == RepeatDirection.FORWARD) {
            append(":");
        }

        append("\" ");

        return stringBuilder;
    }
}
