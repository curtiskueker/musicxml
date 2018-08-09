package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.ClefSign;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.time.Time;

import java.util.List;

public class AttributesBuilder extends MusicDataBuilder {
    public StringBuilder buildAttributes(Attributes attributes) throws BuildException {
        AttributesUtil.setCurrentAttributes(attributes);

        List<Key> keys = attributes.getKeys();
        for(Key key : keys) {
            MusicDataBuilder keyBuilder = new MusicDataBuilder(key);
            append(keyBuilder.build().toString());
        }

        List<Time> timeList = attributes.getTimeList();
        for(Time time : timeList) {
            MusicDataBuilder timeBuilder = new MusicDataBuilder(time);
            append(timeBuilder.build().toString());
        }

        for (Clef clef : attributes.getClefs()) {
            if (!TypeUtil.getBooleanDefaultYes(clef.getPrintObject())) continue;

            append("\\clef \"");
            ClefSign clefSign = clef.getSign();
            Integer line = clef.getLine();
            switch (clefSign) {
                case G:
                    if(line == 2) append("treble");
                    break;
                case F:
                    if(line == 4) append("bass");
                    break;
                case C:
                    if(line == 3) append("alto");
                    else if (line == 4) append("tenor");
                    break;
                case PERCUSSION:
                    append("percussion");
                    break;
                default:
                    throw new BuildException("ClefSign " + clefSign + " not implemented");
            }

            Integer clefOctaveChange = clef.getClefOctaveChange();
            if (clefOctaveChange != null && !clefOctaveChange.equals(0)) {
                if (clefOctaveChange < 0) {
                    append("_");
                } else {
                    append("^");
                }

                clefOctaveChange = Math.abs(clefOctaveChange);
                Integer stepValue = 1 + (clefOctaveChange * 7);
                append(String.valueOf(stepValue));
            }

            appendLine("\"");

            if (clefSign == ClefSign.PERCUSSION) appendLine("\\set Staff.middleCPosition = #-6");
        }

        return stringBuilder;
    }
}
