package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.barline.BarStyle;
import org.curtis.musicxml.barline.BarStyleColor;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.barline.RepeatDirection;

public class BarlineBuilder extends MusicDataBuilder {
    private Barline barline;

    public BarlineBuilder(Barline barline) {
        super(barline);
        this.barline = barline;
    }

    public StringBuilder build() {
        BarStyleColor barStyleColor = barline.getBarStyle();
        BarStyle barStyle = barStyleColor.getBarStyle();
        Repeat repeat = barline.getRepeat();
        RepeatDirection repeatDirection = repeat == null ? null : repeat.getDirection();

        append(" \\bar \"");

       if(repeatDirection == RepeatDirection.BACKWARD) {
            append(":");
        }

        // bar style
        if(barStyle != null) {
           switch (barStyle) {
               case LIGHT_HEAVY:
                   append("|.");
                   break;
           }
        }

        if(repeatDirection == RepeatDirection.FORWARD) {
            append(":");
        }

        append("\"");

        return stringBuilder;
    }
}
