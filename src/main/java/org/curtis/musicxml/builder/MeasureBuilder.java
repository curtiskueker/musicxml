package org.curtis.musicxml.builder;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.builder.musicdata.AttributesBuilder;
import org.curtis.musicxml.builder.musicdata.BackupBuilder;
import org.curtis.musicxml.builder.musicdata.BarlineBuilder;
import org.curtis.musicxml.builder.musicdata.BookmarkBuilder;
import org.curtis.musicxml.builder.musicdata.DirectionBuilder;
import org.curtis.musicxml.builder.musicdata.FiguredBassBuilder;
import org.curtis.musicxml.builder.musicdata.ForwardBuilder;
import org.curtis.musicxml.builder.musicdata.GroupingBuilder;
import org.curtis.musicxml.builder.musicdata.HarmonyBuilder;
import org.curtis.musicxml.builder.musicdata.LinkBuilder;
import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.builder.musicdata.NoteBuilder;
import org.curtis.musicxml.builder.musicdata.PrintBuilder;
import org.curtis.musicxml.builder.musicdata.SoundBuilder;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Grouping;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.FiguredBass;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;

public class MeasureBuilder extends MusicDataBuilder {
    private Measure measure;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        buildOpenElement("measure");
        buildAttribute("number", measure.getNumber());
        buildAttribute("implicit", measure.getImplicit());
        buildAttribute("non-controlling", measure.getNonControlling());
        buildAttribute("width", measure.getWidth());
        buildCloseElement();
        for (MusicData musicData : measure.getMusicDataList()) {
            BaseBuilder baseBuilder = null;
            if (musicData instanceof Note) baseBuilder = new NoteBuilder((Note)musicData);
            else if (musicData instanceof Backup) baseBuilder = new BackupBuilder((Backup)musicData);
            else if (musicData instanceof Forward) baseBuilder = new ForwardBuilder((Forward)musicData);
            else if (musicData instanceof Direction) baseBuilder = new DirectionBuilder((Direction)musicData);
            else if (musicData instanceof Attributes) baseBuilder = new AttributesBuilder((Attributes)musicData);
            else if (musicData instanceof Harmony) baseBuilder = new HarmonyBuilder((Harmony)musicData);
            else if (musicData instanceof FiguredBass) baseBuilder = new FiguredBassBuilder((FiguredBass)musicData);
            else if (musicData instanceof Print) baseBuilder = new PrintBuilder((Print)musicData);
            else if (musicData instanceof Sound) baseBuilder = new SoundBuilder((Sound)musicData);
            else if (musicData instanceof Barline) baseBuilder = new BarlineBuilder((Barline)musicData);
            else if (musicData instanceof Grouping) baseBuilder = new GroupingBuilder((Grouping)musicData);
            else if (musicData instanceof Link) baseBuilder = new LinkBuilder((Link)musicData);
            else if (musicData instanceof Bookmark) baseBuilder = new BookmarkBuilder((Bookmark)musicData);
            if (baseBuilder != null) append(baseBuilder.build().toString());
        }
        buildEndElement("measure");

        return stringBuilder;
    }
}
