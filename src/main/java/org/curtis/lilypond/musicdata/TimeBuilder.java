package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;

import java.util.List;

public class TimeBuilder extends MusicDataBuilder {
    public TimeBuilder() {

    }

    public StringBuilder buildTimeSignature(TimeSignature timeSignature) throws BuildException {
        List<TimeSignatureType> timeSignatureList = timeSignature.getTimeSignatureList();
        for(TimeSignatureType timeSignatureType : timeSignatureList) {
            append("\\time ");
            append(timeSignatureType.getBeats());
            append("/");
            appendLine(timeSignatureType.getBeatType());
        }

        return stringBuilder;
    }
}
