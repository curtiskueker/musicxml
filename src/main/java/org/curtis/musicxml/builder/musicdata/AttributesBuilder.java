package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.ClefSign;
import org.curtis.musicxml.attributes.Time;
import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.key.TraditionalKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributesBuilder extends MusicDataBuilder {
    private Attributes attributes;
    private static final Map<Integer, String> MAJOR_KEY_MAP;
    private static final Map<Integer, String> MINOR_KEY_MAP;

    static {
        MAJOR_KEY_MAP = new HashMap<>();
        MAJOR_KEY_MAP.put(-6, "ges");
        MAJOR_KEY_MAP.put(-5, "des");
        MAJOR_KEY_MAP.put(-4, "aes");
        MAJOR_KEY_MAP.put(-3, "ees");
        MAJOR_KEY_MAP.put(-2, "bes");
        MAJOR_KEY_MAP.put(-1, "f");
        MAJOR_KEY_MAP.put(0, "c");
        MAJOR_KEY_MAP.put(1, "g");
        MAJOR_KEY_MAP.put(2, "d");
        MAJOR_KEY_MAP.put(3, "a");
        MAJOR_KEY_MAP.put(4, "e");
        MAJOR_KEY_MAP.put(5, "b");
        MAJOR_KEY_MAP.put(6, "fis");

        MINOR_KEY_MAP = new HashMap<>();
        MINOR_KEY_MAP.put(-6, "ees");
        MINOR_KEY_MAP.put(-5, "b");
        MINOR_KEY_MAP.put(-4, "f");
        MINOR_KEY_MAP.put(-3, "c");
        MINOR_KEY_MAP.put(-2, "g");
        MINOR_KEY_MAP.put(-1, "d");
        MINOR_KEY_MAP.put(0, "a");
        MINOR_KEY_MAP.put(1, "e");
        MINOR_KEY_MAP.put(2, "b");
        MINOR_KEY_MAP.put(3, "fis");
        MINOR_KEY_MAP.put(4, "cis");
        MINOR_KEY_MAP.put(5, "gis");
        MINOR_KEY_MAP.put(6, "dis");
    }

    public AttributesBuilder(Attributes attributes) {
        super(attributes);
        this.attributes = attributes;
    }

    public StringBuilder build() {
        List<Key> keys = attributes.getKeys();
        for(Key key : keys) {
            TraditionalKey traditionalKey = key.getTraditionalKey();
            if(traditionalKey != null) {
                append("\\key ");

                String mode = traditionalKey.getMode();
                if(mode.equals("major")) {
                    append(MAJOR_KEY_MAP.get(traditionalKey.getFifths()));
                } else if(mode.equals("minor")) {
                    append(MINOR_KEY_MAP.get(traditionalKey.getFifths()));
                }
                append(" \\");
                appendLine(mode);
            }
        }

        List<Time> timeList = attributes.getTimeList();
        for(Time time : timeList) {
            List<TimeSignature> timeSignatures = time.getTimeSignatures();
            for(TimeSignature timeSignature : timeSignatures) {
                setCurrentTimeSignature(timeSignature);

                append("\\time ");
                append(timeSignature.getBeats());
                append("/");
                appendLine(timeSignature.getBeatType());
            }
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
                    break;
            }
        }

        return stringBuilder;
    }
}
