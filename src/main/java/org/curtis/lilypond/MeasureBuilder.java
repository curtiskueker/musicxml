package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.DurationException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.musicdata.DirectionBuilder;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.common.OrderedGroup;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.direction.directiontype.OctaveShift;
import org.curtis.musicxml.direction.directiontype.Rehearsal;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.direction.directiontype.metronome.Metronome;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.Chord;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.lilypond.part.RepeatBlock;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class MeasureBuilder extends LilypondBuilder {
    private Measure measure;
    private List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
    private Note previousNote;
    private Note currentNote;
    private List<Direction> currentDirections = new ArrayList<>();
    private Barline currentBarline = null;
    private Chord currentChord = null;
    private TupletNotes currentTuplet = null;
    private TupletNotes lastTuplet = null;
    private String currentVoice;
    private String voice;
    private String defaultVoice;
    private BigDecimal wholeMeasureDuration = MathUtil.ZERO;
    private BigDecimal measureDuration = MathUtil.ZERO;
    private BigDecimal voiceDuration = MathUtil.ZERO;
    private BigDecimal unhandledMeasureDuration = MathUtil.ZERO;
    private Map<String, BigDecimal> voiceDurations = new HashMap<>();
    public static String CURRENT_MEASURE_NUMBER;
    private boolean isFirstMeasure = false;
    private boolean isLastMeasure = false;
    private RepeatBlock repeatBlock;
    private Map<Note, OrderedGroup> noteTuplets = new HashMap<>();
    private boolean hasStartRepeat = false;
    private boolean hasEndRepeat = false;

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

    public OrderedGroup getNoteTuplet(Note note) {
        return noteTuplets.get(note);
    }

    public void setNoteTuplet(Note note, OrderedGroup orderedGroup) {
        noteTuplets.put(note, orderedGroup);
    }

    public boolean isHasStartRepeat() {
        return hasStartRepeat;
    }

    public void setHasStartRepeat(boolean hasStartRepeat) {
        this.hasStartRepeat = hasStartRepeat;
    }

    public boolean isHasEndRepeat() {
        return hasEndRepeat;
    }

    public void setHasEndRepeat(boolean hasEndRepeat) {
        this.hasEndRepeat = hasEndRepeat;
    }

    public StringBuilder build() throws BuildException {
        clearBuilder();

        CURRENT_MEASURE_NUMBER = measure.getNumber();

        displayMeasure();
        if (DEBUG) System.err.println("Measure " + measure.getNumber());

        setWholeMeasureDuration();
        boolean hasValidVoiceDuration = true;

        // create data builder list for processing
        for(MusicData musicData : measure.getMusicDataList()) {
            MusicDataBuilder musicDataBuilder = null;

            setCurrentVoice(musicData);

            if(musicData instanceof Note) {
                currentNote = (Note)musicData;
                BigDecimal currentNoteDuration = currentNote.getDuration();
                FullNote fullNote = currentNote.getFullNote();
                String currentNoteVoice = currentNote.getEditorial().getVoice();
                if (StringUtil.isEmpty(currentNoteVoice)) currentNoteVoice = "1";

                OrderedGroup chordType = fullNote.getChordType();
                if (!isChordNote(currentNote)) {
                    measureDuration = MathUtil.add(measureDuration, currentNoteDuration);
                    if (isCurrentVoice()) voiceDuration = MathUtil.add(voiceDuration, currentNoteDuration);
                    if (MathUtil.largerThan(voiceDuration, wholeMeasureDuration)) hasValidVoiceDuration = false;
                }

                if (!isCurrentVoice()) {
                    if (!isChordNote(currentNote)) voiceDurations.merge(currentVoice, currentNoteDuration, MathUtil::add);
                    continue;
                }

                // chords and tuplets
                OrderedGroup tupletType = getNoteTuplet(currentNote);
                lastTuplet = null;
                if (chordType != null || tupletType != null) {
                    if (chordType != null) {
                        switch (chordType) {
                            case FIRST:
                                currentChord = new Chord();
                                currentChord.setVoice(currentNoteVoice);
                            case MIDDLE:
                                currentChord.getNotes().add(currentNote);
                                break;
                            case LAST:
                                transferDirections();
                                if (tupletType == OrderedGroup.LAST || tupletType == OrderedGroup.MIDDLE) {
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
                            case FIRST:
                                currentTuplet = new TupletNotes();
                                currentTuplet.setVoice(currentNoteVoice);
                            case MIDDLE:
                                if (chordType == null) transferDirections();
                                break;
                            case LAST:
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
                        currentNote.getGrace().setGraceType(OrderedGroup.FIRST);
                    } else {
                        currentNote.getGrace().setGraceType(OrderedGroup.LAST);
                        if(previousNote.getGrace().getGraceType() == OrderedGroup.LAST) {
                            previousNote.getGrace().setGraceType(OrderedGroup.MIDDLE);
                        }
                    }
                } else {
                    closeGraceNote();
                }

                previousNote = currentNote;
            } else if(musicData instanceof Direction) {
                Direction direction = (Direction)musicData;
                Editorial editorial = direction.getEditorial();
                if (StringUtil.isEmpty(editorial.getVoice())) editorial.setVoice(defaultVoice);
                if (isCurrentVoice(editorial.getVoice())) {
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
                setWholeMeasureDuration();
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
        closeGraceNote();

        // Check whether expected duration equals total duration
        // First or last measure can be partial, otherwise it's an exception
        // Calculate expected divisions in the measure
        if(MathUtil.equalTo(wholeMeasureDuration, voiceDuration)) {
            measure.setImplicit(false);
        } else  {
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
            if (isFirstMeasure || isLastMeasure){
                BigDecimal maxVoiceDurationDifference = getMaxMeasureVoiceDurationDifference();
                if (MathUtil.isPositive(maxVoiceDurationDifference)) addSpacerForDurationDifference(maxVoiceDurationDifference);
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
        TimeSignatureType currentTimeSignature = TimeSignatureUtil.getCurrentTimeSignature();
        if(currentTimeSignature != null && TypeUtil.getBoolean(measure.getImplicit())) {
            try {
                BigDecimal numerator = MathUtil.multiply(MathUtil.divide(measureDuration, wholeMeasureDuration), MathUtil.newBigDecimal(currentTimeSignature.getBeats()));
                BigDecimal denominator = MathUtil.newBigDecimal(currentTimeSignature.getBeatType());
                String wholeMeasureRepresentation = TimeSignatureUtil.getWholeMeasureRepresentation(numerator, denominator);

                append("\\partial ");
                appendLine(wholeMeasureRepresentation);
            } catch (DurationException e) {
                displayMeasureMessage(measure, "Expected measure duration doesn't match notated duration.  Skipping partial measure notation.");
            }
        }

        // Main data builder processing loops
        // general list first, then each build each voice
        if (!hasValidVoiceDuration) {
            if (MathUtil.largerThan(voiceDuration, wholeMeasureDuration)) displayMeasureMessage(measure, "Voice duration " + MathUtil.truncate(voiceDuration) + " exceeds expected measure duration " + MathUtil.truncate(wholeMeasureDuration));
            else displayMeasureMessage(measure, "Voice duration extends beyond end of measure");
            appendWholeMeasureSpacerRepresentation();
        } else {
            if (hasNoteDataBuilder()) {
                for (MusicDataBuilder musicDataBuilder : musicDataBuilders) {
                    append(musicDataBuilder.build().toString());
                    BigDecimal unhandledDuration = musicDataBuilder.getUnhandledDuration();
                    if (MathUtil.isPositive(unhandledDuration)) {
                        displayMeasureMessage(measure, "Unhandled duration: " + MathUtil.truncate(unhandledDuration));
                        unhandledMeasureDuration = MathUtil.add(unhandledMeasureDuration, unhandledDuration);
                    }
                }
            } else {
                if (MathUtil.equalTo(wholeMeasureDuration, measureDuration)) {
                    displayMeasureMessage(measure, "Unable to resolve note durations.  Using whole measure spacer.");
                    appendWholeMeasureSpacerRepresentation();
                } else {
                    try {
                        append(TimeSignatureUtil.getSpacerRepresentation(measureDuration));
                    } catch (DurationException e) {
                        displayMeasureMessage(measure, "Unable to resolve spacer representation, duration " + MathUtil.truncate(measureDuration) + ".  Using whole measure spacer.");
                        appendWholeMeasureSpacerRepresentation();
                    }
                }
            }
        }

        if (MathUtil.isPositive(unhandledMeasureDuration)) {
            displayMeasureMessage(measure, "Total duration unhandled in measure: " + MathUtil.truncate(unhandledMeasureDuration) + ".  Appending spacer.");
            MusicDataBuilder spacerDataBuilder = getSpacerDataBuilder(unhandledMeasureDuration);
            try {
                String unhandledData = spacerDataBuilder.build().toString();
                if (StringUtil.isNotEmpty(unhandledData)) {
                    append(unhandledData);
                } else {
                    displayMeasureMessage(measure, "Unable to resolve unhandled measure duration.  Replacing measure with whole measure spacer.");
                    appendWholeMeasureSpacerRepresentation();
                }
            } catch (BuildException e) {
                // handled above
            }
        }

        if (DEBUG) if (!isLastMeasure) append(" | ");

        appendLine();

        return stringBuilder;
    }

    private void closeGraceNote() {
        if(previousNote != null && previousNote.isGraceNote()) {
            if(previousNote.getGrace().getGraceType() == OrderedGroup.FIRST) {
                previousNote.getGrace().setGraceType(OrderedGroup.SINGLETON);
            } else {
                previousNote.getGrace().setGraceType(OrderedGroup.LAST);
            }
        }
    }

    private MusicDataBuilder addToDataBuilders(MusicData musicData) {
        MusicDataBuilder musicDataBuilder = null;
        if (isCurrentVoice() || musicData instanceof Attributes || musicData instanceof Barline) {
            checkVoiceDuration();

            musicDataBuilder = new MusicDataBuilder(musicData);
            musicDataBuilders.add(musicDataBuilder);
        }

        return musicDataBuilder;
    }

    private boolean hasNoteDataBuilder() {
        return musicDataBuilders.stream().map(MusicDataBuilder::getMusicData).anyMatch(musicData -> musicData instanceof Note || musicData instanceof Chord || musicData instanceof TupletNotes);
    }

    private void setWholeMeasureDuration() {
        if (TimeSignatureUtil.getCurrentTimeSignature() == null) return;

        try {
            wholeMeasureDuration = TimeSignatureUtil.getWholeMeasureDuration();
        } catch (TimeSignatureException e) {
            // ignore
        }
    }

    private void checkVoiceDuration() {
        if (MathUtil.isPositive(voiceDuration) && MathUtil.smallerThan(voiceDuration, MathUtil.min(measureDuration, wholeMeasureDuration))) {
            BigDecimal durationDifference = MathUtil.subtract(measureDuration, voiceDuration);
            addSpacerForDurationDifference(durationDifference);
        }
    }

    private BigDecimal getMaxMeasureVoiceDurationDifference() {
        Optional<Map.Entry<String, BigDecimal>> durationEntries = voiceDurations.entrySet().stream().max(Map.Entry.comparingByValue());
        BigDecimal maxVoiceDuration = durationEntries.isPresent() ? durationEntries.get().getValue() : MathUtil.ZERO;

        if (MathUtil.largerThan(maxVoiceDuration, measureDuration)) return measureDuration;
        return MathUtil.subtract(maxVoiceDuration, voiceDuration);
    }

    private void addSpacerForDurationDifference(BigDecimal duration) {
        displayMeasureMessage(measure, "Duration total for measure is short by " + MathUtil.truncate(duration) + ".  Adding spacer.");
        addSpacerDataBuilder(duration);
        voiceDuration = MathUtil.add(voiceDuration, duration);
    }

    private void addSpacerDataBuilder(BigDecimal duration) {
        musicDataBuilders.add(getSpacerDataBuilder(duration));
    }

    private MusicDataBuilder getSpacerDataBuilder(BigDecimal duration) {
        Note spacerNote = NoteUtil.getSpacerNote(duration);

        return new MusicDataBuilder(spacerNote);
    }

    private void setCurrentVoice(MusicData musicData) {
        if (musicData instanceof Note) {
            Note note = (Note)musicData;
            Editorial editorial = note.getEditorial();
            if (editorial != null) {
                String musicDataVoice = editorial.getVoice();
                if(StringUtil.isNotEmpty(musicDataVoice)) currentVoice = musicDataVoice;
            }
        } else if (musicData instanceof Forward) {
            Forward forward = (Forward)musicData;
            Editorial editorial = forward.getEditorial();
            if (editorial != null) {
                String musicDataVoice = editorial.getVoice();
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

    private boolean isCurrentVoice(String musicDataVoice) {
        // voice matches or, when not found, voice = default voice
        return musicDataVoice.equals(currentVoice) || (StringUtil.isEmpty(currentVoice) && musicDataVoice.equals(defaultVoice));
    }

    private boolean isChordNote(Note note) {
        OrderedGroup chordType = note.getFullNote().getChordType();

        return chordType != null && chordType != OrderedGroup.LAST;
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
                else if (directionType instanceof Words && TypeUtil.getBoolean(direction.getDirective())) isDeferred = false;
                else if (directionType instanceof Rehearsal) isDeferred = false;
                else if (directionType instanceof OctaveShift) isDeferred = false;

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
        displayMeasure();

        try {
            for (MusicDataBuilder musicDataBuilder : musicDataBuilders) {
                Object musicData = musicDataBuilder.getMusicData();
                if (musicData instanceof Attributes) append(musicDataBuilder.build().toString());
            }
            append(TimeSignatureUtil.getWholeMeasureSpacerRepresentation());
        } catch (Exception e) {
            // skip
            e.printStackTrace();
        }
    }

    private void displayMeasure() {
        append("% measure ");
        appendLine(CURRENT_MEASURE_NUMBER);
    }

    private void clearBuilder() {
        clear();
        musicDataBuilders.clear();
        measureDuration = MathUtil.ZERO;
        voiceDuration = MathUtil.ZERO;
    }
}
