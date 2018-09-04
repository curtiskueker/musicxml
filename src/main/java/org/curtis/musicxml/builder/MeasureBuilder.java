package org.curtis.musicxml.builder;

import org.curtis.database.DBException;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.bin.MusicXmlUtil;
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
        for (MeasureItem measureItem : measure.getMeasureItems()) {
            BaseBuilder baseBuilder = null;
            Integer measureItemId = measureItem.getId();
            String musicDataType = measureItem.getMusicDataType();
            try {
                if (musicDataType.equals("note")) baseBuilder = new NoteBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Note.class, measureItemId));
                else if (musicDataType.equals("backup")) baseBuilder = new BackupBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Backup.class, measureItemId));
                else if (musicDataType.equals("forward")) baseBuilder = new ForwardBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Forward.class, measureItemId));
                else if (musicDataType.equals("direction")) baseBuilder = new DirectionBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Direction.class, measureItemId));
                else if (musicDataType.equals("attributes")) baseBuilder = new AttributesBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Attributes.class, measureItemId));
                else if (musicDataType.equals("harmony")) baseBuilder = new HarmonyBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Harmony.class, measureItemId));
                else if (musicDataType.equals("figured bass")) baseBuilder = new FiguredBassBuilder(MusicXmlUtil.getDbTransaction().getObjectById(FiguredBass.class, measureItemId));
                else if (musicDataType.equals("print")) baseBuilder = new PrintBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Print.class, measureItemId));
                else if (musicDataType.equals("sound")) baseBuilder = new SoundBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Sound.class, measureItemId));
                else if (musicDataType.equals("barline")) baseBuilder = new BarlineBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Barline.class, measureItemId));
                else if (musicDataType.equals("grouping")) baseBuilder = new GroupingBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Grouping.class, measureItemId));
                else if (musicDataType.equals("link")) baseBuilder = new LinkBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Link.class, measureItemId));
                else if (musicDataType.equals("bookmark")) baseBuilder = new BookmarkBuilder(MusicXmlUtil.getDbTransaction().getObjectById(Bookmark.class, measureItemId));
            } catch (DBException e) {
                e.printStackTrace();
            }
            if (baseBuilder != null) append(baseBuilder.build().toString());
        }
        buildEndElement("measure");

        return stringBuilder;
    }
}
