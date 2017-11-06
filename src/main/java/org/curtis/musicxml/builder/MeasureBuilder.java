package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.builder.musicdata.NoteBuilder;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MeasureBuilder extends AbstractBuilder {
    private Measure measure;
    private List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
    private Note previousNote;
    private Set<Integer> currentBeams = new HashSet<>();
    private boolean deferEndBeam = false;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        List<MusicData> musicDataList = measure.getMusicDataList();

        // go through notes and mark begins and ends
        for(MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder;
            if(musicData instanceof Note) {
                // chords
                Note currentNote = (Note)musicData;
                FullNote fullNote = currentNote.getFullNote();

                //TODO: handle grace notes
                if(currentNote.getGrace() != null) {
                    continue;
                }

                musicDataBuilder = new NoteBuilder(currentNote);
                if (previousNote != null) {
                    if(fullNote.getChord() && !previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setBeginChord(true);
                    } else if(fullNote.getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setContinueChord(true);
                    } else if(!fullNote.getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setEndChord(true);
                    }
                }

                // beams
                List<Beam> beams = currentNote.getBeams();
                for (Beam beam : beams) {
                    Integer beamNumber = beam.getNumber();
                    BeamType beamType = beam.getType();
                    switch (beamType) {
                        case BEGIN:
                            if(currentBeams.isEmpty()) {
                                currentNote.setBeginBeam(true);
                            }
                            currentBeams.add(beamNumber);
                            break;
                        case END:
                            currentBeams.remove(beamNumber);
                            if(currentBeams.isEmpty()) {
                                currentNote.setEndBeam(true);
                            }
                            break;
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

        // adjust end beams so they don't end up within chord
        for(MusicData musicData : musicDataList) {
            if(musicData instanceof Note) {
                Note note = (Note)musicData;
                FullNote fullNote = note.getFullNote();
                if((fullNote.isBeginChord() || fullNote.isContinueChord()) && note.getEndBeam()) {
                    note.setEndBeam(false);
                    deferEndBeam = true;
                }
                if(fullNote.isEndChord() && deferEndBeam) {
                    note.setEndBeam(true);
                    deferEndBeam = false;
                }
            }
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
