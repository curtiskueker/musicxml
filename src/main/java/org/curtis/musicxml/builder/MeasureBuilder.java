package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.DirectionBuilder;
import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.builder.musicdata.NoteBuilder;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Notations;
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
    private List<Notations> currentNotationsList = new ArrayList<>();

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
                        previousNote.getFullNote().setChord(true);
                        previousNote.getFullNote().setChordType(Connection.START);
                    } else if(fullNote.getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setChordType(Connection.CONTINUE);
                    } else if(!fullNote.getChord() && previousNote.getFullNote().getChord()) {
                        previousNote.getFullNote().setChordType(Connection.STOP);
                    }
                }

                // grace notes
                if (currentNote.isGraceNote()) {
                    if(previousNote == null || !previousNote.isGraceNote()) {
                        currentNote.getGrace().setGraceType(Connection.START);
                    } else {
                        currentNote.getGrace().setGraceType(Connection.STOP);
                        if(previousNote.getGrace().getGraceType() == Connection.STOP) {
                            previousNote.getGrace().setGraceType(Connection.CONTINUE);
                        }
                    }
                } else {
                    if(previousNote != null && previousNote.isGraceNote()) {
                        if(previousNote.getGrace().getGraceType() == Connection.START) {
                            previousNote.getGrace().setGraceType(Connection.SINGLE);
                        } else {
                            previousNote.getGrace().setGraceType(Connection.STOP);
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
                Direction direction = (Direction)musicData;
                // defer directions until end of next note
                currentDirections.add(direction);
                continue;
            } else {
                musicDataBuilder = new MusicDataBuilder(musicData);
            }

            musicDataBuilders.add(musicDataBuilder);

            transferDirections();
        }

        // clear any directions at the end of the measure
        transferDirections();

        // set chord at end of measure
        if(previousNote != null && previousNote.getFullNote().getChord()) {
            previousNote.getFullNote().setChordType(Connection.STOP);
        }

        // end grace notes at end of measure
        if(previousNote != null && previousNote.isGraceNote()) {
            if(previousNote.getGrace().getGraceType() == Connection.START) {
                previousNote.getGrace().setGraceType(Connection.SINGLE);
            } else {
                previousNote.getGrace().setGraceType(Connection.STOP);
            }
        }

        // adjust end beams and notations so they don't end up within chord
        for(MusicData musicData : musicDataList) {
            if(musicData instanceof Note) {
                Note note = (Note)musicData;
                FullNote fullNote = note.getFullNote();
                if((fullNote.getChordType() == Connection.START || fullNote.getChordType() == Connection.CONTINUE)) {
                    if (note.getEndBeam()) {
                        note.setEndBeam(false);
                        deferEndBeam = true;
                    }

                    List<Notations> notationsList = note.getNotationsList();
                    currentNotationsList.addAll(notationsList);
                    notationsList.clear();
                }
                if(fullNote.getChordType() == Connection.STOP) {
                    if (deferEndBeam) {
                        note.setEndBeam(true);
                        deferEndBeam = false;
                    }

                    List<Notations> notationsList = note.getNotationsList();
                    notationsList.addAll(currentNotationsList);
                    currentNotationsList.clear();
                }
            }
        }

        List<MusicDataBuilder> musicDataBuildersTemp = new ArrayList<>();
        Note currentNote = null;
        for(MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            // adjust the music data builders so that directions appear after chords
            if(musicDataBuilder instanceof NoteBuilder) {
                NoteBuilder noteBuilder = (NoteBuilder)musicDataBuilder;
                currentNote = noteBuilder.getNote();
                musicDataBuildersTemp.add(noteBuilder);
                if(!currentNote.getFullNote().getChord() || currentNote.getFullNote().getChordType() == Connection.STOP) {
                    for(DirectionBuilder directionBuilder : currentDirectionBuilders) {
                        musicDataBuildersTemp.add(directionBuilder);
                    }
                    currentDirectionBuilders.clear();
                }
            } else if(musicDataBuilder instanceof DirectionBuilder) {
                DirectionBuilder directionBuilder = (DirectionBuilder)musicDataBuilder;
                if(currentNote != null && (currentNote.getFullNote().getChordType() == Connection.START || currentNote.getFullNote().getChordType() == Connection.CONTINUE)) {
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
