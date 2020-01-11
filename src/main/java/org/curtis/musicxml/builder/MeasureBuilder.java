package org.curtis.musicxml.builder;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
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
import org.curtis.musicxml.score.MeasureItem;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class MeasureBuilder extends MusicDataBuilder {
    private Measure measure;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        String measureNumber = measure.getNumber();
        if (DEBUG) System.err.println("Measure " + measureNumber);
        buildOpenElement("measure");
        buildAttribute("number", BuilderUtil.requiredValue(measureNumber));
        buildAttribute("implicit", measure.getImplicit());
        buildAttribute("non-controlling", measure.getNonControlling());
        buildAttribute("width", measure.getWidth());
        buildCloseElement();
        for (MeasureItem measureItem : measure.getMeasureItems()) {
            BaseBuilder baseBuilder = null;
            String musicDataType = measureItem.getMusicDataType();
            try {
                switch (musicDataType) {
                    case "note":
                        baseBuilder = new NoteBuilder((Note)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "backup":
                        baseBuilder = new BackupBuilder((Backup)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "forward":
                        baseBuilder = new ForwardBuilder((Forward)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "direction":
                        baseBuilder = new DirectionBuilder((Direction)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "attributes":
                        baseBuilder = new AttributesBuilder((Attributes)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "harmony":
                        baseBuilder = new HarmonyBuilder((Harmony)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "figured bass":
                        baseBuilder = new FiguredBassBuilder((FiguredBass)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "print":
                        baseBuilder = new PrintBuilder((Print)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "sound":
                        baseBuilder = new SoundBuilder((Sound)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "barline":
                        baseBuilder = new BarlineBuilder((Barline)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "grouping":
                        baseBuilder = new GroupingBuilder((Grouping)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "link":
                        baseBuilder = new LinkBuilder((Link)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                    case "bookmark":
                        baseBuilder = new BookmarkBuilder((Bookmark)MusicXmlUtil.getMusicDataForMeasureItem(measureItem));
                        break;
                }
            } catch (MusicXmlException e) {
                e.printStackTrace();
            }
            if (baseBuilder != null) append(baseBuilder.build().toString());
        }
        buildEndElement("measure");

        return stringBuilder;
    }
}
