package org.curtis.lilypond.part;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.lilypond.exception.BuildException;
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

import static org.curtis.musicxml.handler.ScoreHandler.DEBUG;

public class PartBuilder extends AbstractBuilder {
    private Part part;
    private Measure previousMeasure;
    private Note previousNote;
    private Map<String, Boolean> tupletsOn = new HashMap<>();
    private Measure currentRepeatStartBlockMeasure;
    private Measure currentRepeatEndBlockMeasure;
    private Integer currentEndingCount = 0;
    private List<RepeatBlock> currentRepeatBlocks = new ArrayList<>();
    private boolean hasLyrics = false;
    private WavyLine stopWavyLine = null;
    private boolean hasStopWavyLine = false;
    private Map<String, Set<Integer>> activeSlurs = new HashMap<>();

    public static Attributes CURRENT_ATTRIBUTES;
    public static String CURRENT_PART_ID;

    public PartBuilder(Part part) {
        this.part = part;
        CURRENT_PART_ID = part.getPartId();
    }

    public StringBuilder build() throws BuildException {
        if (DEBUG) System.err.println("Part " + part.getPartId());
        List<Measure> measures = part.getMeasures();

        // pre-processing loop
        // check for endings
        // check for vocal part: presence of lyric is indicator
        for(Measure measure : measures) {
            boolean hasEnding = false;
            // default repeat block measure, in case opening repeat not notated
            if (currentRepeatStartBlockMeasure == null) currentRepeatStartBlockMeasure = measure;

            List<MusicData> musicDataList = measure.getMusicDataList();
            for(MusicData musicData : musicDataList) {
                musicData.setStaffNumber(measure.getStaffNumber());

                if(musicData instanceof Note) {
                    Note note = (Note)musicData;
                    FullNote fullNote = note.getFullNote();

                    if(skipNote(note)) {
                        continue;
                    }

                    String voice = note.getEditorialVoice().getVoice();
                    if (StringUtil.isNotEmpty(voice)) measure.getVoices().add(voice);
                    if (!note.getLyrics().isEmpty()) hasLyrics = true;

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
                                Set<Integer> activeVoiceSlurs = activeSlurs.computeIfAbsent(voice, voiceSlurs -> new HashSet<Integer>());
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
                                note.setTupletType(Connection.START);
                                tupletsOn.put(voice, true);
                                break;
                            case STOP:
                                note.setTupletType(Connection.STOP);
                                tupletsOn.put(voice, false);
                                break;
                        }
                    } else if(note.getFullNote().isChord() && previousNote.getFullNote().isChord() && previousNote.getTupletType() == Connection.STOP) {
                        // adjust end tuplet on chords
                        previousNote.setTupletType(Connection.CONTINUE);
                        note.setTupletType(Connection.STOP);
                    } else if(tupletsOn.computeIfAbsent(voice, voiceTuplet -> false)) {
                        note.setTupletType(Connection.CONTINUE);
                    }

                    previousNote = note;
                } else if(musicData instanceof Barline) {
                    Barline barline = (Barline)musicData;
                    Ending ending = barline.getEnding();
                    if(ending != null) {
                        hasEnding = true;
                        switch (ending.getType()) {
                            case START:
                                RepeatBlock startRepeatBlock = currentRepeatStartBlockMeasure.getRepeatBlock();
                                if (startRepeatBlock == null) {
                                    startRepeatBlock = new RepeatBlock();
                                }
                                startRepeatBlock.setRepeatBlockType(RepeatBlockType.MAIN);
                                currentEndingCount++;
                                startRepeatBlock.setEndingCount(currentEndingCount);
                                currentRepeatStartBlockMeasure.setRepeatBlock(startRepeatBlock);

                                if(currentRepeatStartBlockMeasure.getNumber().equals(previousMeasure.getNumber())) {
                                    startRepeatBlock.setConnectionType(Connection.SINGLE);
                                } else{
                                    startRepeatBlock.setConnectionType(Connection.START);

                                    if(currentRepeatEndBlockMeasure == null) {
                                        RepeatBlock endRepeatBlock = new RepeatBlock();
                                        endRepeatBlock.setConnectionType(Connection.STOP);
                                        endRepeatBlock.setRepeatBlockType(RepeatBlockType.MAIN);
                                        endRepeatBlock.setEndingCount(currentEndingCount);
                                        previousMeasure.setRepeatBlock(endRepeatBlock);
                                        currentRepeatEndBlockMeasure = previousMeasure;
                                    } else {
                                        RepeatBlock currentRepeatEndBlock = currentRepeatEndBlockMeasure.getRepeatBlock();
                                        currentRepeatEndBlock.setEndingCount(currentEndingCount);
                                    }
                                }

                                RepeatBlock currentRepeatBlock = new RepeatBlock();
                                currentRepeatBlock.setRepeatBlockType(RepeatBlockType.ENDING);
                                currentRepeatBlock.setConnectionType(Connection.START);
                                currentRepeatBlock.setEndingNumber(currentEndingCount);
                                measure.setRepeatBlock(currentRepeatBlock);
                                currentRepeatBlocks.add(currentRepeatBlock);
                                break;
                            case STOP:
                            case DISCONTINUE:
                                currentRepeatBlock = measure.getRepeatBlock();
                                if (currentRepeatBlock == null) {
                                    currentRepeatBlock = new RepeatBlock();
                                    currentRepeatBlock.setRepeatBlockType(RepeatBlockType.ENDING);
                                    currentRepeatBlock.setConnectionType(Connection.STOP);
                                    currentRepeatBlock.setEndingNumber(currentEndingCount);
                                    measure.setRepeatBlock(currentRepeatBlock);
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
                                currentRepeatStartBlockMeasure = measure;
                                break;
                        }
                    }
                }
            }

            if (previousMeasure != null && !hasEnding) {
                RepeatBlock previousRepeatBlock = previousMeasure.getRepeatBlock();
                if(previousRepeatBlock != null && previousRepeatBlock.getRepeatBlockType() == RepeatBlockType.ENDING &&
                        (previousRepeatBlock.getConnectionType() == Connection.STOP || previousRepeatBlock.getConnectionType() == Connection.SINGLE)) {
                    currentRepeatStartBlockMeasure = measure;
                    currentRepeatEndBlockMeasure = null;
                    currentEndingCount = 0;
                    currentRepeatBlocks.clear();
                }
            }

            // close last chord note at end of measure
            if(previousNote != null && previousNote.getFullNote().isChord()) {
                previousNote.getFullNote().setChordType(Connection.STOP);
            }

            previousMeasure = measure;
        }

        // main processing loop
        if(hasLyrics) {
            LyricPartBuilder lyricPartBuilder = new LyricPartBuilder(part);
            append(lyricPartBuilder.build().toString());
        } else {
            VoicePartBuilder voicePartBuilder = new VoicePartBuilder(part);
            append(voicePartBuilder.build().toString());
        }

        return stringBuilder;
    }

    public static boolean skipNote(Note note) {
        // skip cues and non-printed chords as redundant
        return note.getCue() || !note.getPrintout().getPrintObject();
    }
}
