package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.builder.AbstractBuilder;
import org.curtis.musicxml.note.Note;
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
        }

        if (builder != null) {
            builder.setValues(getCurrentTimeSignature());
            stringBuilder = builder.build();
            setValues(builder.getCurrentTimeSignature());
        }

        return stringBuilder;
    }
}
