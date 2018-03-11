package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;

public class MeasureBuilder extends BaseBuilder {
    private Measure measure;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        append("<measure");
        buildAttribute("number", measure.getNumber());
        appendLine(">");
        for (MusicData musicData : measure.getMusicDataList()) {
            // TODO: music-data
        }
        appendLine("</measure>");
        return stringBuilder;
    }
}
