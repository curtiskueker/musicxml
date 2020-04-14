package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.BarlineRepeat;
import org.curtis.musicxml.barline.BarlineRepeatDirection;

public class BarlineBuilder extends MusicDataBuilder {
    public StringBuilder buildBarline(Barline barline) throws BuildException {
        BarlineRepeat repeat = barline.getRepeat();
        BarlineRepeatDirection repeatDirection = repeat == null ? null : repeat.getDirection();

        append(" \\bar \"");

       if(repeatDirection == BarlineRepeatDirection.BACKWARD) {
            append(":");
        }

        // bar style
        BarStyle barStyle = barline.getBarStyle();
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

        if(repeatDirection == BarlineRepeatDirection.FORWARD) {
            append(":");
        }

        append("\" ");

        return stringBuilder;
    }
}
