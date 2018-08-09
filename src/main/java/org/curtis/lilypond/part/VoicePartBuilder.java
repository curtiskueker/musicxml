package org.curtis.lilypond.part;

import org.curtis.lilypond.MeasureBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.SlurType;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.RepeatBlock;
import org.curtis.musicxml.score.RepeatBlockType;
import org.curtis.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class VoicePartBuilder extends FilteredPartBuilder {
    private Part part;
    private int currentVoiceCount = 0;
    private List<MeasureBuilder> measureBuilderList = new ArrayList<>();
    private List<MeasureBuilder> currentMeasureBuilderList = new ArrayList<>();
    private MeasureBuilder currentRepeatStartBlockMeasureBuilder;
    private MeasureBuilder currentRepeatEndBlockMeasureBuilder;
    private Integer currentEndingCount = 0;
    private List<RepeatBlock> currentRepeatBlocks = new ArrayList<>();
    private MeasureBuilder previousMeasureBuilder;
    private Note previousNote;
    private Map<String, Boolean> tupletsOn = new HashMap<>();
    private WavyLine stopWavyLine = null;
    private boolean hasStopWavyLine = false;
    private Map<String, Set<Integer>> activeSlurs = new HashMap<>();

    public VoicePartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        appendLine("{");

        // Pre-processing specific to VoicePartBuilder measure handling
        for(Measure measure : part.getMeasures()) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);

            boolean hasEnding = false;
            // default repeat block measure, in case opening repeat not notated
            if (currentRepeatStartBlockMeasureBuilder == null) currentRepeatStartBlockMeasureBuilder = measureBuilder;

            List<MusicData> musicDataList = measure.getMusicDataList();
            for(MusicData musicData : musicDataList) {
                if(musicData instanceof Note) {
                    Note note = (Note) musicData;
                    FullNote fullNote = note.getFullNote();

                    if(skipNote(note)) {
                        continue;
                    }

                    String voice = note.getEditorialVoice().getVoice();
                    if (StringUtil.isNotEmpty(voice)) measureBuilder.getVoices().add(voice);

                    // notation/ornament adjustments
                    boolean addNewNotations = hasStopWavyLine;

                    hasStopWavyLine = false;
                    TrillMark trillMark = null;
                    Ornaments trillMarkOrnaments = null;
                    WavyLine startWavyLine = null;
                    List<Ornament> wavyLineOrnamentList = null;
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
                            }
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

        if (measureBuilderList.isEmpty()) throw new BuildException("VoicePartBuilder: empty MeasureBuilder list");

        measureBuilderList.get(0).isFirstMeasure();
        measureBuilderList.get(measureBuilderList.size() -1).isLastMeasure();

        // Process the measures
        for(MeasureBuilder measureBuilder : measureBuilderList) {
            try {
                if (isVoiceCountChange(measureBuilder) || isStartRepeatBlock(measureBuilder)) {
                    processMeasures();
                }

                currentMeasureBuilderList.add(measureBuilder);
                currentVoiceCount = measureBuilder.getVoices().size();

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

    private boolean isVoiceCountChange(MeasureBuilder measureBuilder) {
        int measureVoiceCount = measureBuilder.getVoices().size();

        return currentVoiceCount > 0 && measureVoiceCount != currentVoiceCount;
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

    private void processMeasures() throws BuildException {
        if (currentMeasureBuilderList.isEmpty()) return;

        SortedSet<String> measureVoices = currentMeasureBuilderList.get(0).getVoices();
        // TODO: process when no voice elements are found
        if (measureVoices.isEmpty()) return;

        boolean hasMultipleVoices = measureVoices.size() > 1;
        String defaultVoice = measureVoices.first();

        Attributes currentAttributes = AttributesUtil.attributesCopy(PartBuilder.CURRENT_ATTRIBUTES);
        for (String voice : measureVoices) {
            // Reset attributes state at the beginning of each voice
            AttributesUtil.setCurrentAttributes(currentAttributes);

            // Begin repeat endings
            MeasureBuilder firstMeasureBuilder = currentMeasureBuilderList.get(0);
            if (isMainRepeatBlock(firstMeasureBuilder) && isStartRepeatBlock(firstMeasureBuilder) && voice.equals(measureVoices.first())) {
                append("\\repeat volta #");
                append(String.valueOf(firstMeasureBuilder.getRepeatBlock().getEndingCount()));
                appendLine(" {");
            } else if(isEndingRepeatBlock(firstMeasureBuilder) && isStartRepeatBlock(firstMeasureBuilder)) {
                if (firstMeasureBuilder.getRepeatBlock().getEndingNumber().equals(1)) {
                    appendLine("\\alternative {");
                }
                appendLine("{");
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
            if (isEndRepeatBlock(lastMeasure)) {
                appendLine();
                appendLine("}");

                RepeatBlock repeatBlock = lastMeasure.getRepeatBlock();
                if (isEndingRepeatBlock(lastMeasure) && repeatBlock.getEndingNumber().equals(repeatBlock.getEndingCount())) {
                    appendLine("}");
                }
            }
        }

        currentMeasureBuilderList.clear();
    }

    public static boolean skipNote(Note note) {
        // skip cues and non-printed chords as redundant
        return note.getCue() || !TypeUtil.getBooleanDefaultYes(note.getPrintout().getPrintObject());
    }
}
