package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.PartBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.ClefSign;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.key.Key;

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
        }

        return stringBuilder;
    }
}
