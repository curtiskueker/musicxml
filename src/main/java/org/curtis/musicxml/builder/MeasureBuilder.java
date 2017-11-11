package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.DirectionBuilder;
import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.builder.musicdata.NoteBuilder;
import org.curtis.musicxml.direction.Direction;
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
    private List<Direction> currentDirections = new ArrayList<>();
    private List<DirectionBuilder> currentDirectionBuilders = new ArrayList<>();

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        List<MusicData> musicDataList = measure.getMusicDataList();

        // go through notes and mark begins and ends
        for(MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder;
            if(musicData instanceof Note) {
                Note currentNote = (Note)musicData;
                FullNote fullNote = currentNote.getFullNote();

                musicDataBuilder = new NoteBuilder(currentNote);

                if (previousNote != null) {
                    // chords
                    if(fullNote.getChord() && !previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setBeginChord(true);
                    } else if(fullNote.getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setContinueChord(true);
                    } else if(!fullNote.getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setEndChord(true);
                    }

                    // grace notes
                    if (currentNote.getGrace() != null) {
                        if(previousNote.getGrace() == null) {
                            currentNote.setBeginGrace(true);
                        } else {
                            currentNote.setEndGrace(true);
                            if(previousNote.isEndGrace()) {
                                previousNote.setEndGrace(false);
                                previousNote.setContinueGrace(true);
                            }
                        }
                    } else {
                        if(previousNote.getGrace() != null) {
                            previousNote.setEndGrace(true);
                        }
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
            } else if(musicData instanceof Direction) {
                // defer directions until end of next note
                Direction direction = (Direction)musicData;
                currentDirections.add(direction);
                continue;
            } else {
                musicDataBuilder = new MusicDataBuilder(musicData);
            }

            musicDataBuilders.add(musicDataBuilder);

            transferDirections();
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

        // adjust the music data builders so that directions appear after chords
        List<MusicDataBuilder> musicDataBuildersTemp = new ArrayList<>();
        Note currentNote = null;
        for(MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            if(musicDataBuilder instanceof NoteBuilder) {
                NoteBuilder noteBuilder = (NoteBuilder)musicDataBuilder;
                currentNote = noteBuilder.getNote();
                musicDataBuildersTemp.add(noteBuilder);
                if(!currentNote.getFullNote().isBeginChord() && !currentNote.getFullNote().isContinueChord()) {
                    for(DirectionBuilder directionBuilder : currentDirectionBuilders) {
                        musicDataBuildersTemp.add(directionBuilder);
                    }
                    currentDirectionBuilders.clear();
                }
            } else if(musicDataBuilder instanceof DirectionBuilder) {
                DirectionBuilder directionBuilder = (DirectionBuilder)musicDataBuilder;
                if(currentNote != null && (currentNote.getFullNote().isBeginChord() || currentNote.getFullNote().isContinueChord())) {
                    currentDirectionBuilders.add(directionBuilder);
                } else {
                    musicDataBuildersTemp.add(directionBuilder);
                }
            } else {
                musicDataBuildersTemp.add(musicDataBuilder);
            }
        }
        musicDataBuilders = new ArrayList<>(musicDataBuildersTemp);

        // main music data handling loop
        for(MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            musicDataBuilder.setValues(getCurrentTimeSignature());
            append(musicDataBuilder.build().toString());
            setValues(musicDataBuilder.getCurrentTimeSignature());
        }

        appendLine("");

        return stringBuilder;
    }

    private void transferDirections() {
        for(Direction direction : currentDirections) {
            DirectionBuilder directionBuilder = new DirectionBuilder(direction);
            musicDataBuilders.add(directionBuilder);
        }

        currentDirections.clear();
    }
}
