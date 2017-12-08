package org.curtis.lilypond;

import org.curtis.musicxml.barline.Barline;
import org.curtis.lilypond.musicdata.BarlineBuilder;
import org.curtis.lilypond.musicdata.ChordBuilder;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.lilypond.musicdata.NoteBuilder;
import org.curtis.lilypond.musicdata.PrintBuilder;
import org.curtis.lilypond.musicdata.TupletBuilder;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.ChordNotes;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.RepeatBlock;
import org.curtis.musicxml.score.RepeatBlockType;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MeasureBuilder extends AbstractBuilder {
    private Measure measure;
    private List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
    private Note previousNote;
    private Set<Integer> currentBeams = new HashSet<>();
    private List<Direction> currentDirections = new ArrayList<>();
    private Barline currentBarline = null;
    private BigDecimal currentBackupDuration = MathUtil.ZERO;
    private boolean lastNoteSkipped = false;
    private boolean tupletsOn = false;
    private ChordNotes currentChord = null;
    private TupletNotes currentTuplet = null;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        List<MusicData> musicDataList = measure.getMusicDataList();

        append("% measure ");
        appendLine(measure.getNumber());

        // pre-processing loop
        // go through notes and mark begins and ends of chords and tuplets
        // these are grouped into their own builder calls
        for(MusicData musicData : musicDataList) {
            if(musicData instanceof Note) {
                Note currentNote = (Note)musicData;
                FullNote fullNote = currentNote.getFullNote();

                if (previousNote != null) {
                    // chord type
                    if(fullNote.isChord() && !previousNote.getFullNote().isChord()) {
                        previousNote.getFullNote().setChord(true);
                        previousNote.getFullNote().setChordType(Connection.START);
                    } else if(fullNote.isChord() && previousNote.getFullNote().isChord()) {
                        previousNote.getFullNote().setChordType(Connection.CONTINUE);
                    } else if(!fullNote.isChord() && previousNote.getFullNote().isChord()) {
                        previousNote.getFullNote().setChordType(Connection.STOP);
                    }
                }

                // tuplet type
                Tuplet tuplet = currentNote.getTuplet();
                if(tuplet != null) {
                    Connection tupletType = tuplet.getType();
                    switch (tupletType) {
                        case START:
                            currentNote.setTupletType(Connection.START);
                            tupletsOn = true;
                            break;
                        case STOP:
                            currentNote.setTupletType(Connection.STOP);
                            tupletsOn = false;
                            break;
                    }
                } else if(currentNote.getFullNote().isChord() && previousNote.getFullNote().isChord() && previousNote.getTupletType() == Connection.STOP) {
                    // adjust end tuplet on chords
                    previousNote.setTupletType(Connection.CONTINUE);
                    currentNote.setTupletType(Connection.STOP);
                } else if(tupletsOn) {
                    currentNote.setTupletType(Connection.CONTINUE);
                }

                previousNote = currentNote;
            }
        }

        // close last chord note at end of measure
        if(previousNote != null && previousNote.getFullNote().isChord()) {
            previousNote.getFullNote().setChordType(Connection.STOP);
        }

        // create data builder list for processing
        for(MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder = null;

            if(musicData instanceof Note) {
                Note currentNote = (Note)musicData;
                FullNote fullNote = currentNote.getFullNote();

                // skip if we're in backup mode
                boolean skipNote = MathUtil.isPositive(currentBackupDuration);
                if (skipNote || (lastNoteSkipped && fullNote.isChord())) {
                    if (!fullNote.isChord()) {
                        currentBackupDuration = MathUtil.subtract(currentBackupDuration, currentNote.getDuration());
                    }
                    lastNoteSkipped = true;
                    continue;
                }

                // chords and tuplets
                Connection chordType = fullNote.getChordType();
                Connection tupletType = currentNote.getTupletType();
                if (chordType != null || tupletType != null) {
                    if (chordType != null) {
                        switch (chordType) {
                            case START:
                                currentChord = new ChordNotes();
                            case CONTINUE:
                                currentChord.getNotes().add(currentNote);
                                break;
                            case STOP:
                                currentChord.getNotes().add(currentNote);
                                currentChord.getDirections().addAll(currentDirections);
                                currentDirections.clear();
                                if (tupletType == Connection.STOP || tupletType == Connection.CONTINUE) {
                                    currentTuplet.getMusicDataList().add(currentChord);
                                } else {
                                    musicDataBuilder = new ChordBuilder(currentChord);
                                }
                                currentChord = null;
                                break;
                        }
                    }

                    if (tupletType != null) {
                        switch (tupletType) {
                            case START:
                                currentTuplet = new TupletNotes();
                            case CONTINUE:
                                if (chordType == null) {
                                    currentTuplet.getMusicDataList().add(currentNote);
                                    currentTuplet.getMusicDataList().addAll(currentDirections);
                                    currentDirections.clear();
                                }
                                break;
                            case STOP:
                                if (chordType == null) {
                                    currentTuplet.getMusicDataList().add(currentNote);
                                    currentTuplet.getMusicDataList().addAll(currentDirections);
                                    currentDirections.clear();
                                }
                                musicDataBuilder = new TupletBuilder(currentTuplet);
                                currentTuplet = null;
                                break;
                        }
                    }
                } else {
                    musicDataBuilder = new NoteBuilder(currentNote);
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

                lastNoteSkipped = false;
                previousNote = currentNote;
            } else if(musicData instanceof Direction) {
                Direction direction = (Direction)musicData;
                // defer directions until end of next note
                currentDirections.add(direction);
                continue;
            } else if(musicData instanceof Barline) {
                // hold on the final barline until the very end
                Barline barline = (Barline)musicData;
                Location barlineLocation = barline.getLocation();
                if (barlineLocation == Location.RIGHT) {
                    currentBarline = barline;
                    continue;
                }

                musicDataBuilder = new BarlineBuilder(barline);
            } else if (musicData instanceof Backup) {
                Backup backup = (Backup)musicData;
                currentBackupDuration = MathUtil.add(currentBackupDuration, backup.getDuration());
                continue;
            } else if (musicData instanceof Forward) {
                Forward forward = (Forward)musicData;
                currentBackupDuration = MathUtil.subtract(currentBackupDuration, forward.getDuration());
                continue;
            }else if(musicData instanceof Print) {
                Print print = (Print) musicData;
                musicDataBuilder = new PrintBuilder(print);
            } else {
                musicDataBuilder = new MusicDataBuilder(musicData);
            }

            if (musicDataBuilder != null) {
                musicDataBuilders.add(musicDataBuilder);
                transferDirections();
            }
        }

        // clear any directions at the end of the measure
        transferDirections();

        // put any barline at the end
        if(currentBarline != null) {
            BarlineBuilder barlineBuilder = new BarlineBuilder(currentBarline);
            musicDataBuilders.add(barlineBuilder);
        }

        // end grace notes at end of measure
        if(previousNote != null && previousNote.isGraceNote()) {
            if(previousNote.getGrace().getGraceType() == Connection.START) {
                previousNote.getGrace().setGraceType(Connection.SINGLE);
            } else {
                previousNote.getGrace().setGraceType(Connection.STOP);
            }
        }

        // Process output
        // Begin repeat endings
        RepeatBlock repeatBlock = measure.getRepeatBlock();
        if(repeatBlock != null) {
            RepeatBlockType repeatBlockType = repeatBlock.getRepeatBlockType();
            Connection repeatBlockConnectionType = repeatBlock.getConnectionType();
            if (repeatBlockType == RepeatBlockType.MAIN && (repeatBlockConnectionType == Connection.START || repeatBlockConnectionType == Connection.SINGLE)) {
                append("\\repeat volta #");
                append(String.valueOf(repeatBlock.getEndingCount()));
                appendLine(" {");
            } else if(repeatBlockType == RepeatBlockType.ENDING &&(repeatBlockConnectionType == Connection.START || repeatBlockConnectionType == Connection.SINGLE)) {
                if (repeatBlock.getEndingNumber().equals(1)) {
                    appendLine("\\alternative {");
                }
                appendLine("{");
            }
        }

        // Main data builder processing loop
        for(MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            append(musicDataBuilder.build().toString());
        }

        // End repeat endings
        if(repeatBlock != null) {
            RepeatBlockType repeatBlockType = repeatBlock.getRepeatBlockType();
            Connection repeatBlockConnectionType = repeatBlock.getConnectionType();
            if (repeatBlockConnectionType == Connection.STOP || repeatBlockConnectionType == Connection.SINGLE) {
                appendLine("");
                appendLine("}");

                if (repeatBlockType == RepeatBlockType.ENDING && repeatBlock.getEndingNumber().equals(repeatBlock.getEndingCount())) {
                    appendLine("}");
                }
            }
        }

        appendLine("");

        return stringBuilder;
    }

    private void transferDirections() {
        for(Direction direction : currentDirections) {
            musicDataBuilders.add(new MusicDataBuilder(direction));
        }

        currentDirections.clear();
    }
}
