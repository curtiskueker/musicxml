package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.builder.AbstractBuilder;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.score.MusicData;

public class MusicDataBuilder extends AbstractBuilder {
    private MusicData musicData;

    public MusicDataBuilder(MusicData musicData) {
        this.musicData = musicData;
    }

    public StringBuilder build() {
        MusicDataBuilder builder = null;
        if(musicData instanceof Attributes) {
            builder = new AttributesBuilder((Attributes)musicData);
        } else if(musicData instanceof Note) {
            builder = new NoteBuilder((Note)musicData);
        } else if(musicData instanceof Direction) {
            builder = new DirectionBuilder((Direction)musicData);
        } else if(musicData instanceof Barline) {
            builder = new BarlineBuilder((Barline)musicData);
        } else if(musicData instanceof Print) {
            builder = new PrintBuilder((Print)musicData);
        } else if(musicData instanceof TupletNotes) {
            builder = new TupletBuilder((TupletNotes)musicData);
        }

        if (builder != null) {
            builder.setValues(getCurrentTimeSignature());
            stringBuilder = builder.build();
            setValues(builder.getCurrentTimeSignature());
        }

        return stringBuilder;
    }
}
