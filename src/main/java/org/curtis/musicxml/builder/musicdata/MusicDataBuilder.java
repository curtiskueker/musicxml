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
        if(musicData instanceof Attributes) {
            AttributesBuilder attributesBuilder = new AttributesBuilder((Attributes)musicData);
            stringBuilder = attributesBuilder.build();
        } else if(musicData instanceof Note) {
            NoteBuilder noteBuilder = new NoteBuilder((Note)musicData);
            stringBuilder =  noteBuilder.build();
        }

        return stringBuilder;
    }
}
