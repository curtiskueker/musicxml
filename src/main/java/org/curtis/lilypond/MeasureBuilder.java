package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.part.PartBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.barline.Barline;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
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
import org.curtis.musicxml.score.RepeatBlockType;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class MeasureBuilder extends AbstractBuilder {
    private Measure measure;
    private List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
    private Note previousNote;
    private Note currentNote;
    private Map<String, Set<Integer>> currentBeams = new HashMap<>();
    private List<Direction> currentDirections = new ArrayList<>();
    private Barline currentBarline = null;
    private Chord currentChord = null;
    private TupletNotes currentTuplet = null;
    private TupletNotes lastTuplet = null;
    private SortedMap<String, List<MusicDataBuilder>> voiceDataBuilders = new TreeMap<>();
    private String currentVoice = null;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() throws BuildException {
        List<MusicData> musicDataList = measure.getMusicDataList();

        append("% measure ");
        appendLine(measure.getNumber());

        BigDecimal totalDuration = MathUtil.ZERO;

        // create data builder list for processing
        for(MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder = null;

            if(musicData instanceof Note) {
                currentNote = (Note)musicData;
                FullNote fullNote = currentNote.getFullNote();
                String voice = currentNote.getEditorialVoice().getVoice();
                if (StringUtil.isEmpty(voice)) voice = "1";

                if(PartBuilder.skipNote(currentNote)) {
                    continue;
                }

                // chords and tuplets
                Connection chordType = fullNote.getChordType();
                Connection tupletType = currentNote.getTupletType();
                lastTuplet = null;
                if (chordType != null || tupletType != null) {
                    if (chordType != null) {
                        switch (chordType) {
                            case START:
                                currentChord = new Chord();
                                currentChord.setVoice(voice);
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
                                currentTuplet.setVoice(voice);
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

                // beams
                List<Beam> beams = currentNote.getBeams();
                for (Beam beam : beams) {
                    Integer beamNumber = beam.getNumber();
                    BeamType beamType = beam.getType();
                    Set<Integer> voiceBeams = currentBeams.computeIfAbsent(voice, beamSet -> new HashSet<>());
                    switch (beamType) {
                        case BEGIN:
                            if(voiceBeams.isEmpty()) {
                                currentNote.setBeginBeam(true);
                            }
                            voiceBeams.add(beamNumber);
                            break;
                        case END:
                            voiceBeams.remove(beamNumber);
                            if(voiceBeams.isEmpty()) {
                                currentNote.setEndBeam(true);
                            }
                            break;
                    }
                }

                if (chordType == null || chordType == Connection.START) {
                    totalDuration = MathUtil.add(totalDuration, currentNote.getDuration());
                }
                previousNote = currentNote;
            } else if(musicData instanceof Direction) {
                Direction direction = (Direction)musicData;
                setDirectionDefaults(direction);
                if (deferredDirection(direction)) {
                    currentDirections.add(direction);
                } else {
                    addToDataBuilders(direction);
                }

                continue;
            } else if(musicData instanceof Barline) {
                lastTuplet = null;
                // hold on the final barline until the very end
                Barline barline = (Barline)musicData;
                Location barlineLocation = barline.getLocation();
                if (barlineLocation == Location.RIGHT) {
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
                totalDuration = MathUtil.subtract(totalDuration, backup.getDuration());
                continue;
            } else if (musicData instanceof Forward) {
                Forward forward = (Forward)musicData;
                totalDuration = MathUtil.add(totalDuration, forward.getDuration());
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

        // clear any directions at the end of the measure
        transferDirections();

        // put any barline at the end
        if(currentBarline != null) {
            addToDataBuilders(currentBarline);
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

        if(!MathUtil.equalTo(wholeMeasureDuration, totalDuration)) {
            if (measure.isFirstMeasure()) {
                measure.setImplicit(true);
            } else if (!measure.isLastMeasure()){
                throw new BuildException(getExceptionStringPrefix(measure) + "Expected duration: " + wholeMeasureDuration + " Total duration: " + totalDuration + ".  Skipping measure.");
            }
        }

        // process polyphonic voice map
        processVoiceBuilders();

        // OUTPUT
        //
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

        // Partial measure
        if(measure.getImplicit()) {
            try {
                BigDecimal numerator = MathUtil.multiply(MathUtil.divide(totalDuration, wholeMeasureDuration), MathUtil.newBigDecimal(currentTimeSignature.getBeats()));
                BigDecimal denominator = MathUtil.newBigDecimal(currentTimeSignature.getBeatType());
                String wholeMeasureRepresentation = TimeSignatureUtil.getWholeMeasureRepresentation(numerator, denominator);

                append("\\partial ");
                appendLine(wholeMeasureRepresentation);
            } catch (TimeSignatureException e) {
                System.err.println(getExceptionStringPrefix(measure) + "Expected measure duration doesn't match notated duration.  Skipping partial measure notation.");
            }
        }

        // Main data builder processing loops
        // general list first, then each build each voice
        for(MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            append(musicDataBuilder.build().toString());
        }
        for(String voiceBuilderKey : voiceDataBuilders.keySet()) {
            List<MusicDataBuilder> voiceDataBuilderList = voiceDataBuilders.get(voiceBuilderKey);
            for (MusicDataBuilder voiceDataBuilder : voiceDataBuilderList) {
                append(voiceDataBuilder.build().toString());
            }
        }

        // End repeat endings
        if(repeatBlock != null) {
            RepeatBlockType repeatBlockType = repeatBlock.getRepeatBlockType();
            Connection repeatBlockConnectionType = repeatBlock.getConnectionType();
            if (repeatBlockConnectionType == Connection.STOP || repeatBlockConnectionType == Connection.SINGLE) {
                appendLine();
                appendLine("}");

                if (repeatBlockType == RepeatBlockType.ENDING && repeatBlock.getEndingNumber().equals(repeatBlock.getEndingCount())) {
                    appendLine("}");
                }
            }
        }

        appendLine();

        return stringBuilder;
    }

    private MusicDataBuilder addToDataBuilders(MusicData musicData) {
        if (musicData instanceof Note) {
            Note note = (Note)musicData;
            EditorialVoice editorialVoice = note.getEditorialVoice();
            if (editorialVoice != null) {
                String voice = editorialVoice.getVoice();
                if(StringUtil.isNotEmpty(voice)) currentVoice = voice;
            }
        } else if (musicData instanceof Forward) {
            Forward forward = (Forward)musicData;
            EditorialVoice editorialVoice = forward.getEditorialVoice();
            if (editorialVoice != null) {
                String voice = editorialVoice.getVoice();
                if(StringUtil.isNotEmpty(voice)) currentVoice = voice;
            }
        } else if (musicData instanceof Chord) {
            Chord chord = (Chord)musicData;
            currentVoice = chord.getVoice();
        } else if (musicData instanceof TupletNotes) {
            TupletNotes tupletNotes = (TupletNotes)musicData;
            currentVoice = tupletNotes.getVoice();
        }

        MusicDataBuilder musicDataBuilder = new MusicDataBuilder(musicData);

        if (StringUtil.isEmpty(currentVoice)) {
            musicDataBuilders.add(new MusicDataBuilder(musicData));
        } else {
            List<MusicDataBuilder> voiceList = voiceDataBuilders.computeIfAbsent(currentVoice, voiceBuilders -> new ArrayList<>());
            voiceList.add(musicDataBuilder);
        }

        return musicDataBuilder;
    }

    private void processVoiceBuilders() {
        if (voiceDataBuilders.size() < 2) return;

        // Mark first and last items in each list as start and stop
        for(List<MusicDataBuilder> voiceList : voiceDataBuilders.values()) {
            voiceList.get(0).getMusicData().setPolyphonicVoiceStart(Connection.START);
            voiceList.get(voiceList.size() - 1).getMusicData().setPolyphonicVoiceStop(Connection.STOP);
        }

        // Mark first item of first voice list, and last item of last voice list as begin and end
        List<MusicDataBuilder> firstList = voiceDataBuilders.get(voiceDataBuilders.firstKey());
        firstList.get(0).getMusicData().setPolyphonicVoiceStart(Connection.BEGIN);
        List<MusicDataBuilder> lastList = voiceDataBuilders.get(voiceDataBuilders.lastKey());
        lastList.get(lastList.size() - 1).getMusicData().setPolyphonicVoiceStop(Connection.END);
    }

    private void transferDirections() {
        if (hasMultipleDirections()) {
            List<Direction> nonMultipleDirections = new ArrayList<>();
            for (Direction direction : currentDirections) {
                boolean isMultipleDirection = false;
                for (DirectionType directionType : direction.getDirectionTypes()) {
                    if (DirectionType.MULTIPLE_DIRECTION_TYPES.contains(directionType.getClass().getSimpleName())) {
                        isMultipleDirection = true;
                        break;
                    }
                }

                if (isMultipleDirection) {
                    currentNote.getMultipleDirections().add(direction);
                } else {
                    nonMultipleDirections.add(direction);
                }
            }

            currentDirections.clear();
            currentDirections.addAll(nonMultipleDirections);
        }

        if (lastTuplet != null) {
            lastTuplet.getMusicDataList().addAll(currentDirections);
        } else if (currentChord != null) {
            currentChord.getNotes().add(currentNote);
            currentChord.getDirections().addAll(currentDirections);
        } else if (currentTuplet != null) {
            currentTuplet.getMusicDataList().add(currentNote);
            currentTuplet.getMusicDataList().addAll(currentDirections);
        } else {
            for(Direction direction : currentDirections) {
                addToDataBuilders(direction);
            }
        }

        currentDirections.clear();
    }

    private boolean hasMultipleDirections() {
        Map<String, Integer> directionTypeCounts = new HashMap<>();
        for (Direction direction : currentDirections) {
            for (DirectionType directionType : direction.getDirectionTypes()) {
                String name = directionType.getClass().getSimpleName();
                Integer directionTypeCount = directionTypeCounts.computeIfAbsent(name, count -> 0);
                directionTypeCount++;
                directionTypeCounts.put(name, directionTypeCount);
            }
        }

        for (String multipleDirectionType : DirectionType.MULTIPLE_DIRECTION_TYPES) {
            Integer directionTypeCount = directionTypeCounts.get(multipleDirectionType);
            if (directionTypeCount != null && directionTypeCount > 1) return true;
        }

        return false;
    }

    private boolean deferredDirection(Direction direction) {
        boolean isDeferred = true;
        if (direction.getDirective()) isDeferred = false;

        for (DirectionType directionType : direction.getDirectionTypes()) {
            if (directionType instanceof Metronome) isDeferred = false;
            if (directionType instanceof Words && direction.getDirective()) isDeferred = false;

            if (!isDeferred) break;
        }

        // reset any relevant direction type values if it's deferred
        if (!isDeferred) {
            direction.setDirective(true);
            for (DirectionType directionType : direction.getDirectionTypes()) {
                if (directionType instanceof Words) {
                    Words words = (Words)directionType;
                    words.setTextMark(true);
                }
            }
        }

        return isDeferred;
    }

    private void setDirectionDefaults(Direction direction) {
        for (DirectionType directionType : direction.getDirectionTypes()) {
            if (directionType instanceof Words) {
                if (!direction.getDirective() && direction.getPlacement() == null) {
                    direction.setPlacement(Location.ABOVE);
                }
            }
        }
    }
}
