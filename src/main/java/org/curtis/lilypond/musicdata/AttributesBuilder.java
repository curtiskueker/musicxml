package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.PartBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.ClefSign;
import org.curtis.musicxml.attributes.Time;
import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.musicxml.attributes.key.Key;

import java.util.List;

public class AttributesBuilder extends MusicDataBuilder {
    public StringBuilder buildAttributes(Attributes attributes) throws BuildException {
        if(PartBuilder.CURRENT_ATTRIBUTES == null) {
            PartBuilder.CURRENT_ATTRIBUTES = attributes;
        }

        List<Key> keys = attributes.getKeys();
        for(Key key : keys) {
            MusicDataBuilder keyBuilder = new MusicDataBuilder(key);
            append(keyBuilder.build().toString());
        }
        if(!keys.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setKeys(keys);
        }

        List<Time> timeList = attributes.getTimeList();
        for(Time time : timeList) {
            List<TimeSignature> timeSignatures = time.getTimeSignatures();
            for(TimeSignature timeSignature : timeSignatures) {
                append("\\time ");
                append(timeSignature.getBeats());
                append("/");
                appendLine(timeSignature.getBeatType());
            }
        }
        if(!timeList.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setTimeList(timeList);
        }

        Clef clef = attributes.getClef();
        if(clef != null) {
            append("\\clef ");
            ClefSign clefSign = clef.getSign();
            Integer line = clef.getLine();
            switch (clefSign) {
                case G:
                    if(line == 2) appendLine("treble");
                    break;
                case F:
                    if(line == 4) appendLine("bass");
                    break;
                case C:
                    if(line == 3) appendLine("alto");
                    else if (line == 4) appendLine("tenor");
                    break;
            }

            PartBuilder.CURRENT_ATTRIBUTES.setClef(clef);
        }

        return stringBuilder;
    }
}
