package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.musicdata.DirectionBuilder;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.lilypond.musicdata.NoteBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.Chord;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.RepeatBlock;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class MeasureBuilder extends AbstractBuilder {
    private Measure measure;
    private List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
    private boolean hasNoteDataBuilder = false;
    private Note previousNote;
    private Note currentNote;
    private Set<Integer> currentBeams = new HashSet<>();
    private List<Direction> currentDirections = new ArrayList<>();
    private Barline currentBarline = null;
    private Chord currentChord = null;
    private TupletNotes currentTuplet = null;
    private TupletNotes lastTuplet = null;
    private String currentVoice;
    private String voice;
    private String defaultVoice;
    private BigDecimal measureDuration = MathUtil.ZERO;
    private BigDecimal voiceDuration = MathUtil.ZERO;
    public static String CURRENT_MEASURE_NUMBER;
    private boolean isFirstMeasure = false;
    private boolean isLastMeasure = false;
    private RepeatBlock repeatBlock;
    private SortedSet<String> voices = new TreeSet<>();
    private Map<Note, Connection> tupletTypes = new HashMap<>();

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getDefaultVoice() {
        return defaultVoice;
    }

    public void setDefaultVoice(String defaultVoice) {
        this.defaultVoice = defaultVoice;
    }

    public void isFirstMeasure() {
        isFirstMeasure = true;
    }

    public void isLastMeasure() {
        isLastMeasure = true;
    }

    public RepeatBlock getRepeatBlock() {
        return repeatBlock;
    }

    public void setRepeatBlock(RepeatBlock repeatBlock) {
        this.repeatBlock = repeatBlock;
    }

    public SortedSet<String> getVoices() {
        return voices;
    }

    public void setVoices(SortedSet<String> voices) {
        this.voices = voices;
    }

    public void setTupletType(Note note, Connection connection) {
        tupletTypes.put(note, connection);
    }

    public Connection getTupletType(Note note) {
        return tupletTypes.get(note);
    }

    public StringBuilder build() throws BuildException {
        clearBuilder();

        CURRENT_MEASURE_NUMBER = measure.getNumber();

        append("% measure ");
        appendLine(CURRENT_MEASURE_NUMBER);

        if (DEBUG) System.err.println("Measure " + measure.getNumber());

        // create data builder list for processing
        for(MusicData musicData : measure.getMusicDataList()) {
            MusicDataBuilder musicDataBuilder = null;

            setCurrentVoice(musicData);

            if(musicData instanceof Note) {
                currentNote = (Note)musicData;
                FullNote fullNote = currentNote.getFullNote();
                String currentNoteVoice = currentNote.getEditorialVoice().getVoice();
                if (StringUtil.isEmpty(currentNoteVoice)) currentNoteVoice = "1";

                Connection chordType = fullNote.getChordType();
                if (chordType == null || chordType == Connection.START) {
                    measureDuration = MathUtil.add(measureDuration, currentNote.getDuration());
                    if (isCurrentVoice()) voiceDuration = MathUtil.add(voiceDuration, currentNote.getDuration());
                }

                if (!isCurrentVoice()) continue;

                if (!TypeUtil.getBooleanDefaultYes(currentNote.getPrintout().getPrintObject()) || currentNote.getCue()) {
                    if (chordType == null || chordType == Connection.START) addSpacerDataBuilder(currentNote.getDuration());
                    continue;
                }

                // chords and tuplets
                Connection tupletType = getTupletType(currentNote);
                lastTuplet = null;
                if (chordType != null || tupletType != null) {
                    if (chordType != null) {
                        switch (chordType) {
                            case START:
                                currentChord = new Chord();
                                currentChord.setVoice(currentNoteVoice);
                            case CONTINUE:
                                currentChord.getNotes().add(currentNote);
                                break;
                            case STOP:
                                transferDirections();
                                if (tupletType == Connection.STOP || tupletType == Connection.CONTINUE) {
                                    currentTuplet.getMusicDataList().add(currentChord);
                                } else {
                                    musicDataBuilder = addToDataBuilders(currentChord);
                                }
                                currentChord = null;
                                break;
                        }
                    }

                    if (tupletType != null) {
                        switch (tupletType) {
                            case START:
                                currentTuplet = new TupletNotes();
                                currentTuplet.setVoice(currentNoteVoice);
                            case CONTINUE:
                                if (chordType == null) transferDirections();
                                break;
                            case STOP:
                                if (chordType == null) transferDirections();
                                musicDataBuilder = addToDataBuilders(currentTuplet);
                                lastTuplet = currentTuplet;
                                currentTuplet = null;
                                break;
                        }
                    }
                } else {
                    musicDataBuilder = addToDataBuilders(currentNote);
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

                previousNote = currentNote;
            } else if(musicData instanceof Direction) {
                if (isCurrentVoice()) {
                    Direction direction = (Direction)musicData;
                    DirectionBuilder.setDirectionDefaults(direction);
                    if (deferredDirection(direction)) {
                        currentDirections.add(direction);
                    } else {
                        addToDataBuilders(direction);
                    }
                }

                continue;
            } else if(musicData instanceof Barline) {
                lastTuplet = null;
                // hold on the final barline until the very end
                Barline barline = (Barline)musicData;
                Location barlineLocation = barline.getLocation();
                if (barlineLocation == null || barlineLocation == Location.RIGHT) {
                    currentBarline = barline;
                    continue;
                }

                musicDataBuilder = addToDataBuilders(barline);
            } else if (musicData instanceof Attributes) {
                Attributes attributes = (Attributes)musicData;
                AttributesUtil.setCurrentAttributes(attributes);
                musicDataBuilder = addToDataBuilders(attributes);
            } else if (musicData instanceof Backup) {
                Backup backup = (Backup)musicData;
                measureDuration = MathUtil.subtract(measureDuration, backup.getDuration());
                continue;
            } else if (musicData instanceof Forward) {
                Forward forward = (Forward)musicData;
                measureDuration = MathUtil.add(measureDuration, forward.getDuration());
                if (isCurrentVoice()) voiceDuration = MathUtil.add(voiceDuration, forward.getDuration());
                musicDataBuilder = addToDataBuilders(forward);
            } else if (musicData instanceof Harmony) {
                // Harmony processed separately
                continue;
            } else {
                musicDataBuilder = addToDataBuilders(musicData);
            }

            if (musicDataBuilder != null) {
                transferDirections();
            }
        }

        // end grace notes at end of measure
        if(previousNote != null && previousNote.isGraceNote()) {
            if(previousNote.getGrace().getGraceType() == Connection.START) {
                previousNote.getGrace().setGraceType(Connection.SINGLE);
            } else {
                previousNote.getGrace().setGraceType(Connection.STOP);
            }
        }

        // Check whether expected duration equals total duration
        // First or last measure can be partial, otherwise it's an exception
        // Calculate expected divisions in the measure
        TimeSignatureType currentTimeSignature = TimeSignatureUtil.getCurrentTimeSignature();
        BigDecimal wholeMeasureDuration;
        try {
            wholeMeasureDuration = TimeSignatureUtil.getWholeMeasureDuration();
        } catch (TimeSignatureException e) {
            throw new BuildException(e.getMessage());
        }

        if(!MathUtil.equalTo(wholeMeasureDuration, voiceDuration)) {
            if (isFirstMeasure) {
                measure.setImplicit(true);
            } else if (!isLastMeasure){
                // Expected voice duration falls short
                // Attempt to add a spacer at the end of the measure
                BigDecimal wholeMeasureDurationDifference = MathUtil.subtract(wholeMeasureDuration, voiceDuration);
                if (MathUtil.isPositive(wholeMeasureDurationDifference)) {
                    addSpacerForDurationDifference(wholeMeasureDurationDifference);
                }
            }
        }

        // clear any directions at the end of the measure
        transferDirections();

        // check that voice duration matches measure duration
        if (!TypeUtil.getBoolean(measure.getImplicit())) checkVoiceDuration();

        // put any barline at the end
        if(currentBarline != null) {
            addToDataBuilders(currentBarline);
        }

        // OUTPUT
        //
        // Partial measure
        if(TypeUtil.getBoolean(measure.getImplicit())) {
            try {
                BigDecimal numerator = MathUtil.multiply(MathUtil.divide(measureDuration, wholeMeasureDuration), MathUtil.newBigDecimal(currentTimeSignature.getBeats()));
                BigDecimal denominator = MathUtil.newBigDecimal(currentTimeSignature.getBeatType());
                String wholeMeasureRepresentation = TimeSignatureUtil.getWholeMeasureRepresentation(numerator, denominator);

                append("\\partial ");
                appendLine(wholeMeasureRepresentation);
            } catch (TimeSignatureException e) {
                displayMeasureMessage(measure, "Expected measure duration doesn't match notated duration.  Skipping partial measure notation.");
            }
        }

        // Main data builder processing loops
        // general list first, then each build each voice
        if (MathUtil.largerThan(measureDuration, wholeMeasureDuration)) {
            System.err.println("Voice duration " + measureDuration + " exceeds expected measure duration " + wholeMeasureDuration + ".  Using whole measure spacer.");
            appendWholeMeasureSpacerRepresentation();
        } else {
            if (hasNoteDataBuilder) {
                for (MusicDataBuilder musicDataBuilder : musicDataBuilders) {
                    append(musicDataBuilder.build().toString());
                }
            } else {
                if (MathUtil.equalTo(wholeMeasureDuration, measureDuration)) {
                    appendWholeMeasureSpacerRepresentation();
                } else {
                    try {
                        append(TimeSignatureUtil.getSpacerRepresentation(measureDuration));
                    } catch (TimeSignatureException e) {
                        System.err.println("Unable to resolve spacer representation, duration " + measureDuration + ".  Using whole measure spacer.");
                        appendWholeMeasureSpacerRepresentation();
                    }
                }
            }
        }

        if (DEBUG) if (!isLastMeasure) append(" | ");

        appendLine();

        return stringBuilder;
    }

    private MusicDataBuilder addToDataBuilders(MusicData musicData) {
        MusicDataBuilder musicDataBuilder = null;
        if (isCurrentVoice() || musicData instanceof Attributes || musicData instanceof Barline) {
            checkVoiceDuration();

            if (musicData instanceof Note) {
                Note note = (Note)musicData;
                NoteBuilder noteBuilder = new NoteBuilder(note);

                // beams
                List<Beam> beams = note.getBeams();
                for (Beam beam : beams) {
                    Integer beamNumber = beam.getNumber();
                    BeamType beamType = beam.getType();
                    switch (beamType) {
                        case BEGIN:
                            if(currentBeams.isEmpty()) {
                                noteBuilder.setBeginBeam();
                            }
                            currentBeams.add(beamNumber);
                            break;
                        case END:
                            currentBeams.remove(beamNumber);
                            if(currentBeams.isEmpty()) {
                                noteBuilder.setEndBeam();
                            }
                            break;
                    }
                }
                musicDataBuilder = noteBuilder;
            } else {
                musicDataBuilder = new MusicDataBuilder(musicData);
            }

            musicDataBuilders.add(musicDataBuilder);

            if (musicData instanceof Note || musicData instanceof Chord) hasNoteDataBuilder = true;
        }

        return musicDataBuilder;
    }

    private void checkVoiceDuration() {
        if (MathUtil.isPositive(voiceDuration) && MathUtil.smallerThan(voiceDuration, measureDuration)) {
            BigDecimal durationDifference = MathUtil.subtract(measureDuration, voiceDuration);
            addSpacerForDurationDifference(durationDifference);
        }
    }

    private void addSpacerForDurationDifference(BigDecimal duration) {
        System.err.println(getPartAndMeasure(measure) + "Voice duration difference in measure: " + duration + ".  Adding spacer note.  Check voice and staff values in notes.");
        addSpacerDataBuilder(duration);
        voiceDuration = MathUtil.add(voiceDuration, duration);
    }

    private void addSpacerDataBuilder(BigDecimal duration) {
        Note spacerNote = NoteUtil.getSpacerNote(duration);
        musicDataBuilders.add(new MusicDataBuilder(spacerNote));
    }

    private void setCurrentVoice(MusicData musicData) {
        if (musicData instanceof Note) {
            Note note = (Note)musicData;
            EditorialVoice editorialVoice = note.getEditorialVoice();
            if (editorialVoice != null) {
                String musicDataVoice = editorialVoice.getVoice();
                if(StringUtil.isNotEmpty(musicDataVoice)) currentVoice = musicDataVoice;
            }
        } else if (musicData instanceof Forward) {
            Forward forward = (Forward)musicData;
            EditorialVoice editorialVoice = forward.getEditorialVoice();
            if (editorialVoice != null) {
                String musicDataVoice = editorialVoice.getVoice();
                if(StringUtil.isNotEmpty(musicDataVoice)) currentVoice = musicDataVoice;
            }
        } else if (musicData instanceof Chord) {
            Chord chord = (Chord)musicData;
            currentVoice = chord.getVoice();
        } else if (musicData instanceof TupletNotes) {
            TupletNotes tupletNotes = (TupletNotes)musicData;
            currentVoice = tupletNotes.getVoice();
        }
    }

    private boolean isCurrentVoice() {
        // voice matches or, when not found, voice = default voice
        return voice.equals(currentVoice) || (StringUtil.isEmpty(currentVoice) && voice.equals(defaultVoice));
    }

    private void transferDirections() {
        if (!isCurrentVoice()) return;

        if (lastTuplet != null) {
            lastTuplet.getMusicDataList().addAll(currentDirections);
        } else if (currentChord != null) {
            currentChord.getNotes().add(currentNote);
            currentChord.getDirections().addAll(currentDirections);
        } else if (currentTuplet != null) {
            currentTuplet.getMusicDataList().add(currentNote);
            currentTuplet.getMusicDataList().addAll(currentDirections);
        } else if (currentNote != null){
            for(Direction direction : currentDirections) {
                currentNote.getDirections().add(direction);
            }
        } else {
            currentDirections.stream().flatMap(typeList -> typeList.getDirectionTypeLists().stream()).flatMap(direction -> direction.getDirectionTypes().stream()).collect(Collectors.toList())
                    .forEach(directionType -> displayMeasureMessage(measure, "Skipping direction type: " + directionType.getClass().getSimpleName()));
        }

        currentDirections.clear();
    }

    private boolean deferredDirection(Direction direction) {
        boolean isDeferred = true;
        if (TypeUtil.getBoolean(direction.getDirective())) isDeferred = false;

        for (DirectionTypeList directionTypeList : direction.getDirectionTypeLists()) {
            for (DirectionType directionType : directionTypeList.getDirectionTypes()) {
                if (directionType instanceof Metronome) isDeferred = false;
                if (directionType instanceof Words && TypeUtil.getBoolean(direction.getDirective())) isDeferred = false;
                if (directionType instanceof Rehearsal) isDeferred = false;

                if (!isDeferred) break;
            }
        }

        // reset any relevant direction type values if it's deferred
        if (!isDeferred) {
            direction.setDirective(true);
            for (DirectionTypeList directionTypeList : direction.getDirectionTypeLists()) {
                for (DirectionType directionType : directionTypeList.getDirectionTypes()) {
                    if (directionType instanceof Words) {
                        Words words = (Words)directionType;
                        words.setTextMark(true);
                    }
                }
            }
        }

        return isDeferred;
    }

    private void appendWholeMeasureSpacerRepresentation() {
        clear();
        try {
            append(TimeSignatureUtil.getWholeMeasureSpacerRepresentation());
        } catch (TimeSignatureException e) {
            // skip
            e.printStackTrace();
        }
    }

    private void clearBuilder() {
        clear();
        musicDataBuilders.clear();
        measureDuration = MathUtil.ZERO;
        voiceDuration = MathUtil.ZERO;
    }
}
