package org.curtis.lilypond.part;

import org.curtis.lilypond.MeasureBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.musicdata.NoteBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.FullNoteType;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.SlurType;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.RepeatBlock;
import org.curtis.musicxml.score.RepeatBlockType;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class VoicePartBuilder extends FilteredPartBuilder {
    private Part part;
    private SortedSet<String> currentVoices = new TreeSet<>();
    private List<MeasureBuilder> measureBuilderList = new ArrayList<>();
    private List<MeasureBuilder> currentMeasureBuilderList = new ArrayList<>();
    private MeasureBuilder currentRepeatStartBlockMeasureBuilder;
    private MeasureBuilder currentRepeatEndBlockMeasureBuilder;
    private Integer currentEndingCount = 0;
    private List<RepeatBlock> currentRepeatBlocks = new ArrayList<>();
    private MeasureBuilder previousMeasureBuilder = null;
    private Note previousNote;
    private Map<String, Boolean> tupletsOn = new HashMap<>();
    private WavyLine stopWavyLine = null;
    private boolean hasStopWavyLine = false;
    private Map<String, Set<Integer>> activeSlurs = new HashMap<>();
    private Map<String, List<Note>> tiedFromNotes = new HashMap<>();
    private Map<String, List<Note>> tiedToNotes = new HashMap<>();

    public VoicePartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        NoteBuilder.CURRENT_BEAMS.clear();

        appendLine("{");

        // Pre-processing specific to VoicePartBuilder measure handling
        for(Measure measure : part.getMeasures()) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);

            boolean hasEnding = false;
            // default repeat block measure, in case opening repeat not notated
            if (currentRepeatStartBlockMeasureBuilder == null) currentRepeatStartBlockMeasureBuilder = measureBuilder;

            for(MusicData musicData : measure.getMusicDataList()) {
                if(musicData instanceof Note) {
                    Note note = (Note) musicData;
                    FullNote fullNote = note.getFullNote();

                    String voice = note.getEditorialVoice().getVoice();
                    if (StringUtil.isNotEmpty(voice)) measureBuilder.getVoices().add(voice);

                    List<Note> tiedFromNoteList = tiedFromNotes.computeIfAbsent(voice, noteVoice -> new ArrayList<>());
                    List<Note> tiedToNoteList = tiedToNotes.computeIfAbsent(voice, noteVoice -> new ArrayList<>());

                    // notation/ornament adjustments
                    boolean addNewNotations = hasStopWavyLine;

                    hasStopWavyLine = false;
                    TrillMark trillMark = null;
                    Ornaments trillMarkOrnaments = null;
                    WavyLine startWavyLine = null;
                    List<Ornament> wavyLineOrnamentList = null;
                    boolean startTieAdded = false;
                    for (Notations notations : note.getNotationsList()) {
                        for (Notation notation : notations.getNotations()) {
                            if (notation instanceof Ornaments) {
                                Ornaments ornaments = (Ornaments)notation;
                                List<Ornament> ornamentList = ornaments.getOrnaments();
                                for (Ornament ornament : ornamentList) {
                                    if (ornament instanceof TrillMark) {
                                        trillMark = (TrillMark)ornament;
                                        trillMarkOrnaments = ornaments;
                                    } else if (ornament instanceof WavyLine) {
                                        WavyLine wavyLine = (WavyLine)ornament;
                                        switch (wavyLine.getType()) {
                                            case START:
                                                startWavyLine = wavyLine;
                                                break;
                                            case STOP:
                                                stopWavyLine = wavyLine;
                                                wavyLineOrnamentList = ornamentList;
                                                hasStopWavyLine = true;
                                                break;
                                        }
                                    }
                                }
                            } else if (notation instanceof Slur) {
                                Slur slur = (Slur)notation;
                                Set<Integer> activeVoiceSlurs = activeSlurs.computeIfAbsent(voice, voiceSlurs -> new HashSet<>());
                                Integer slurNumber = slur.getNumber();
                                if (slurNumber == null) slurNumber = 1;
                                boolean isActiveSlur = activeVoiceSlurs.contains(slurNumber);
                                int activeSlurCount = activeVoiceSlurs.size();
                                switch (slur.getConnectionType()) {
                                    case START:
                                        if (activeSlurCount > 1) {
                                            displayMeasureMessage(measure, "Maximum two active slurs exceeded");
                                            continue;
                                        }
                                        if (isActiveSlur) {
                                            displayMeasureMessage(measure, "Start slur: Slur number " + slurNumber + " already started");
                                            continue;
                                        }
                                        if (activeSlurCount == 1) slur.setSlurType(SlurType.PHRASING);
                                        activeVoiceSlurs.add(slurNumber);
                                        break;
                                    case STOP:
                                        if (!activeVoiceSlurs.contains(slurNumber)) displayMeasureMessage(measure, "Stop slur: Slur number " + slurNumber + " not started");
                                        if (activeSlurCount == 2) slur.setSlurType(SlurType.PHRASING);
                                        activeVoiceSlurs.remove(slurNumber);
                                        break;
                                }
                            } else if (notation instanceof Tied) {
                                Tied tied = (Tied)notation;
                                Connection tiedType = tied.getType();
                                if (tiedType == Connection.START && tiedFromNoteList.isEmpty()) {
                                    tiedFromNoteList.add(note);
                                    startTieAdded = true;
                                }
                            }
                        }
                    }

                    if (!startTieAdded) {
                        if (fullNote.isChord() && !tiedToNoteList.isEmpty()) {
                            tiedToNoteList.add(note);
                        } else if (!fullNote.isChord() && !tiedToNoteList.isEmpty()) {
                            processTies(tiedFromNoteList, tiedToNoteList, note);
                        } else if (fullNote.isChord() && !tiedFromNoteList.isEmpty()) {
                            tiedFromNoteList.add(note);
                        } else if (!fullNote.isChord() && !tiedFromNoteList.isEmpty()) {
                            tiedToNoteList.add(note);
                        }
                    }

                    if (trillMark != null && trillMarkOrnaments != null && startWavyLine != null) trillMarkOrnaments.setPrintObject(false);
                    if (hasStopWavyLine && wavyLineOrnamentList != null) wavyLineOrnamentList.remove(stopWavyLine);
                    if (addNewNotations) {
                        Notations notations = new Notations();
                        notations.setPrintObject(true);
                        Ornaments ornaments = new Ornaments();
                        ornaments.setPrintObject(true);
                        ornaments.getOrnaments().add(stopWavyLine);
                        notations.getNotations().add(ornaments);
                        note.getNotationsList().add(notations);
                    }

                    // chord type
                    if (previousNote != null) {
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
                    Tuplet tuplet = note.getTuplet();
                    if(tuplet != null) {
                        Connection tupletType = tuplet.getType();
                        switch (tupletType) {
                            case START:
                                measureBuilder.setTupletType(note, Connection.START);
                                tupletsOn.put(voice, true);
                                break;
                            case STOP:
                                measureBuilder.setTupletType(note, Connection.STOP);
                                tupletsOn.put(voice, false);
                                break;
                        }
                    } else if(note.getFullNote().isChord() && previousNote.getFullNote().isChord() && measureBuilder.getTupletType(previousNote) == Connection.STOP) {
                        // adjust end tuplet on chords
                        measureBuilder.setTupletType(previousNote, Connection.CONTINUE);
                        measureBuilder.setTupletType(note, Connection.STOP);
                    } else if(tupletsOn.computeIfAbsent(voice, voiceTuplet -> false)) {
                        measureBuilder.setTupletType(note, Connection.CONTINUE);
                    }

                    previousNote = note;
                } else if(musicData instanceof Barline) {
                    Barline barline = (Barline)musicData;
                    Ending ending = barline.getEnding();
                    if(ending != null) {
                        hasEnding = true;
                        switch (ending.getType()) {
                            case START:
                                RepeatBlock startRepeatBlock = currentRepeatStartBlockMeasureBuilder.getRepeatBlock();
                                if (startRepeatBlock == null) {
                                    startRepeatBlock = new RepeatBlock();
                                }
                                startRepeatBlock.setRepeatBlockType(RepeatBlockType.MAIN);
                                currentEndingCount++;
                                startRepeatBlock.setEndingCount(currentEndingCount);
                                currentRepeatStartBlockMeasureBuilder.setRepeatBlock(startRepeatBlock);

                                if(currentRepeatStartBlockMeasureBuilder.getMeasure().getNumber().equals(previousMeasureBuilder.getMeasure().getNumber())) {
                                    startRepeatBlock.setConnectionType(Connection.SINGLE);
                                } else{
                                    startRepeatBlock.setConnectionType(Connection.START);

                                    if(currentRepeatEndBlockMeasureBuilder == null) {
                                        RepeatBlock endRepeatBlock = new RepeatBlock();
                                        endRepeatBlock.setConnectionType(Connection.STOP);
                                        endRepeatBlock.setRepeatBlockType(RepeatBlockType.MAIN);
                                        endRepeatBlock.setEndingCount(currentEndingCount);
                                        previousMeasureBuilder.setRepeatBlock(endRepeatBlock);
                                        currentRepeatEndBlockMeasureBuilder = previousMeasureBuilder;
                                    } else {
                                        RepeatBlock currentRepeatEndBlock = currentRepeatEndBlockMeasureBuilder.getRepeatBlock();
                                        currentRepeatEndBlock.setEndingCount(currentEndingCount);
                                    }
                                }

                                RepeatBlock currentRepeatBlock = new RepeatBlock();
                                currentRepeatBlock.setRepeatBlockType(RepeatBlockType.ENDING);
                                currentRepeatBlock.setConnectionType(Connection.START);
                                currentRepeatBlock.setEndingNumber(currentEndingCount);
                                measureBuilder.setRepeatBlock(currentRepeatBlock);
                                currentRepeatBlocks.add(currentRepeatBlock);
                                break;
                            case STOP:
                            case DISCONTINUE:
                                currentRepeatBlock = measureBuilder.getRepeatBlock();
                                if (currentRepeatBlock == null) {
                                    currentRepeatBlock = new RepeatBlock();
                                    currentRepeatBlock.setRepeatBlockType(RepeatBlockType.ENDING);
                                    currentRepeatBlock.setConnectionType(Connection.STOP);
                                    currentRepeatBlock.setEndingNumber(currentEndingCount);
                                    measureBuilder.setRepeatBlock(currentRepeatBlock);
                                    currentRepeatBlocks.add(currentRepeatBlock);
                                } else {
                                    currentRepeatBlock.setConnectionType(Connection.SINGLE);
                                }
                                break;
                        }

                        currentRepeatBlocks.forEach(repeatBlock -> repeatBlock.setEndingCount(currentEndingCount));
                    }

                    Repeat repeat = barline.getRepeat();
                    if (repeat != null) {
                        switch (repeat.getDirection()) {
                            case FORWARD:
                                currentRepeatStartBlockMeasureBuilder = measureBuilder;
                                break;
                        }
                    }
                } else if (musicData instanceof Attributes) {
                    Attributes attributes = (Attributes)musicData;
                    if (!attributes.getTimeList().isEmpty()) {
                        measureBuilder.isFirstMeasure();
                        if (previousMeasureBuilder != null) previousMeasureBuilder.isLastMeasure();
                    }
                }
            }

            // close last chord note at end of measure
            if(previousNote != null && previousNote.getFullNote().isChord()) {
                previousNote.getFullNote().setChordType(Connection.STOP);
            }

            if (previousMeasureBuilder != null && !hasEnding) {
                RepeatBlock previousRepeatBlock = previousMeasureBuilder.getRepeatBlock();
                if(previousRepeatBlock != null && previousRepeatBlock.getRepeatBlockType() == RepeatBlockType.ENDING &&
                        (previousRepeatBlock.getConnectionType() == Connection.STOP || previousRepeatBlock.getConnectionType() == Connection.SINGLE)) {
                    currentRepeatStartBlockMeasureBuilder = measureBuilder;
                    currentRepeatEndBlockMeasureBuilder = null;
                    currentEndingCount = 0;
                    currentRepeatBlocks.clear();
                }
            }

            measureBuilderList.add(measureBuilder);
            previousMeasureBuilder = measureBuilder;
        }

        if (previousMeasureBuilder != null) previousMeasureBuilder.isLastMeasure();

        if (measureBuilderList.isEmpty()) throw new BuildException("VoicePartBuilder: empty MeasureBuilder list");

        // Process the measures
        for(MeasureBuilder measureBuilder : measureBuilderList) {
            try {
                if (isVoicesChange(measureBuilder) || isStartRepeatBlock(measureBuilder)) {
                    processMeasures();
                }

                currentMeasureBuilderList.add(measureBuilder);
                currentVoices = measureBuilder.getVoices();

                if (isEndRepeatBlock(measureBuilder)) {
                    processMeasures();
                }
            } catch (BuildException e) {
                System.err.println(e.getMessage());
            }
        }

        processMeasures();

        PartBuilder.CURRENT_ATTRIBUTES = null;

        appendLine("}");

        return stringBuilder;
    }

    private boolean isVoicesChange(MeasureBuilder measureBuilder) {
        return currentVoices.size() > 0 && !measureBuilder.getVoices().equals(currentVoices);
    }

    private boolean isStartRepeatBlock(MeasureBuilder measureBuilder) {
        RepeatBlock repeatBlock = measureBuilder.getRepeatBlock();
        return repeatBlock != null && (repeatBlock.getConnectionType() == Connection.START || repeatBlock.getConnectionType() == Connection.SINGLE);
    }

    private boolean isEndRepeatBlock(MeasureBuilder measureBuilder) {
        RepeatBlock repeatBlock = measureBuilder.getRepeatBlock();
        return repeatBlock != null && (repeatBlock.getConnectionType() == Connection.STOP || repeatBlock.getConnectionType() == Connection.SINGLE);
    }

    private boolean isMainRepeatBlock(MeasureBuilder measureBuilder) {
        RepeatBlock repeatBlock = measureBuilder.getRepeatBlock();
        return repeatBlock !=  null && repeatBlock.getRepeatBlockType() == RepeatBlockType.MAIN;
    }

    private boolean isEndingRepeatBlock(MeasureBuilder measureBuilder) {
        RepeatBlock repeatBlock = measureBuilder.getRepeatBlock();
        return repeatBlock !=  null && repeatBlock.getRepeatBlockType() == RepeatBlockType.ENDING;
    }

    private void processTies(List<Note> tiedFromNotes, List<Note> tiedToNotes, Note currentNote) {
        boolean match = false;
        for (Note fromNote :  tiedFromNotes) {
            FullNoteType fromFullNoteType = fromNote.getFullNote().getFullNoteType();
            if (!(fromFullNoteType instanceof Pitch)) continue;
            Pitch fromPitch = (Pitch)fromFullNoteType;
            for (Note toNote : tiedToNotes) {
                FullNoteType toFullNoteType = toNote.getFullNote().getFullNoteType();
                if (!(toFullNoteType instanceof Pitch)) continue;
                Pitch toPitch = (Pitch)toFullNoteType;
                if (fromPitch.getStep() == toPitch.getStep() && MathUtil.equalTo(fromPitch.getAlter(), toPitch.getAlter()) && fromPitch.getOctave().equals(toPitch.getOctave())) {
                    match = true;
                    break;
                }
            }
            if (match) break;
        }
        if (!match) tiedFromNotes.stream().flatMap(fromNote -> fromNote.getTieds().stream()).forEach(tied -> tied.setUnterminated(true));
        tiedFromNotes.clear();
        if (!tiedToNotes.stream().flatMap(toNote -> toNote.getTieds().stream()).filter(tied -> tied.getType() == Connection.CONTINUE || tied.getType() == Connection.START).collect(Collectors.toList()).isEmpty()) {
            tiedFromNotes.addAll(tiedToNotes);
            tiedToNotes.clear();
            tiedToNotes.add(currentNote);
        } else {
            tiedToNotes.clear();
        }
    }

    private void processMeasures() throws BuildException {
        if (currentMeasureBuilderList.isEmpty()) return;

        SortedSet<String> measureVoices = currentMeasureBuilderList.get(0).getVoices();
        String NO_VOICE = "NO VOICE";
        if (measureVoices.isEmpty()) measureVoices.add(NO_VOICE);

        boolean hasMultipleVoices = measureVoices.size() > 1;
        String defaultVoice = measureVoices.first();

        Attributes currentAttributes = AttributesUtil.attributesCopy(PartBuilder.CURRENT_ATTRIBUTES);
        for (String voice : measureVoices) {
            // Reset attributes state at the beginning of each voice
            AttributesUtil.setCurrentAttributes(currentAttributes);

            // Begin repeat endings
            MeasureBuilder firstMeasureBuilder = currentMeasureBuilderList.get(0);
            if (voice.equals(measureVoices.first())) {
                if (isMainRepeatBlock(firstMeasureBuilder) && isStartRepeatBlock(firstMeasureBuilder)) {
                    append("\\repeat volta #");
                    append(String.valueOf(firstMeasureBuilder.getRepeatBlock().getEndingCount()));
                    appendLine(" {");
                } else if(isEndingRepeatBlock(firstMeasureBuilder) && isStartRepeatBlock(firstMeasureBuilder)) {
                    if (firstMeasureBuilder.getRepeatBlock().getEndingNumber().equals(1)) {
                        appendLine("\\alternative {");
                    }
                    appendLine("{");
                }
            }

            if (hasMultipleVoices && voice.equals(measureVoices.first())) {
                appendLine();
                appendLine("<<");
                appendLine("{");
            }

            for (MeasureBuilder measureBuilder : currentMeasureBuilderList) {
                measureBuilder.setVoice(voice);
                measureBuilder.setDefaultVoice(defaultVoice);
                append(measureBuilder.build().toString());
            }

            if (hasMultipleVoices) {
                if (voice.equals(measureVoices.last())) {
                    appendLine();
                    appendLine("}");
                    appendLine(">>");
                } else {
                    appendLine();
                    appendLine("}");
                    appendLine("\\\\");
                    appendLine("{");
                }
            }

            // End repeat endings
            MeasureBuilder lastMeasure = currentMeasureBuilderList.get(currentMeasureBuilderList.size() - 1);
            if (voice.equals(measureVoices.last())) {
                if (isEndRepeatBlock(lastMeasure)) {
                    appendLine();
                    appendLine("}");

                    RepeatBlock repeatBlock = lastMeasure.getRepeatBlock();
                    if (repeatBlock.getEndingNumber().equals(repeatBlock.getEndingCount())) {
                        appendLine("}");
                    }
                }
            }
        }

        currentMeasureBuilderList.clear();
    }
}
