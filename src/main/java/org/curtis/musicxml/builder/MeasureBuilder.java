package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.builder.musicdata.NoteBuilder;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;

import java.util.ArrayList;
import java.util.List;

public class MeasureBuilder extends AbstractBuilder {
    private Measure measure;
    private List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
    private Note previousNote;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        List<MusicData> musicDataList = measure.getMusicDataList();

        // go through notes and mark begin and end chord
        for(MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder;
            if(musicData instanceof Note) {
                Note currentNote = (Note)musicData;
                musicDataBuilder = new NoteBuilder(currentNote);
                if (previousNote != null) {
                    if(currentNote.getFullNote().getChord() && !previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setBeginChord(true);
                    } else if(currentNote.getFullNote().getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setContinueChord(true);
                    } else if(!currentNote.getFullNote().getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setEndChord(true);
                    }
                }
                previousNote = currentNote;
            } else {
                musicDataBuilder = new MusicDataBuilder(musicData);
            }
            musicDataBuilders.add(musicDataBuilder);
        }

        // set chord at end of measure
        if(previousNote != null && previousNote.getFullNote().getChord()) {
            previousNote.getFullNote().setEndChord(true);
        }

        // main music data handling loop
        for(MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            musicDataBuilder.setValues(getCurrentTimeSignature());
            append(musicDataBuilder.build().toString());
            setValues(musicDataBuilder.getCurrentTimeSignature());
        }

        appendLine("");

        return stringBuilder;
    }
}
