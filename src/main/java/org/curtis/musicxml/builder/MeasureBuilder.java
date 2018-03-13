package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.DirectionBuilder;
import org.curtis.musicxml.builder.musicdata.GroupingBuilder;
import org.curtis.musicxml.builder.musicdata.HarmonyBuilder;
import org.curtis.musicxml.builder.musicdata.PrintBuilder;
import org.curtis.musicxml.builder.musicdata.SoundBuilder;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Grouping;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.harmony.Harmony;
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
            BaseBuilder baseBuilder = null;
            if (musicData instanceof Direction) baseBuilder = new DirectionBuilder((Direction)musicData);
            else if (musicData instanceof Harmony) baseBuilder = new HarmonyBuilder((Harmony)musicData);
            else if (musicData instanceof Print) baseBuilder = new PrintBuilder((Print)musicData);
            else if (musicData instanceof Sound) baseBuilder = new SoundBuilder((Sound)musicData);
            else if (musicData instanceof Grouping) baseBuilder = new GroupingBuilder((Grouping)musicData);
            if (baseBuilder != null) append(baseBuilder.build().toString());
        }
        appendLine("</measure>");

        return stringBuilder;
    }
}
