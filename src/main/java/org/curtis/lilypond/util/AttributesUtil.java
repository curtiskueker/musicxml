package org.curtis.lilypond.util;

import org.curtis.lilypond.PartBuilder;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.time.Time;

import java.util.List;

public class AttributesUtil {
    private AttributesUtil() {

    }

    public static void setCurrentAttributes(Attributes attributes) {
        if(attributes == null) return;

        if(PartBuilder.CURRENT_ATTRIBUTES == null) {
            PartBuilder.CURRENT_ATTRIBUTES = attributes;
            return;
        }

        List<Key> keys = attributes.getKeys();
        if(!keys.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setKeys(keys);
        }

        List<Time> timeList = attributes.getTimeList();
        if(!timeList.isEmpty()) {
            PartBuilder.CURRENT_ATTRIBUTES.setTimeList(timeList);
        }

        Clef clef = attributes.getClef();
        if(clef != null) {
            PartBuilder.CURRENT_ATTRIBUTES.setClef(clef);
        }
    }
}
