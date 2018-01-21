package org.curtis.lilypond.part;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.lilypond.MeasureBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.RepeatBlock;
import org.curtis.musicxml.score.RepeatBlockType;

import java.util.ArrayList;
import java.util.List;

public class PartBuilder extends AbstractBuilder {
    private Part part;
    private Measure previousMeasure;
    private Note previousNote;
    private boolean tupletsOn = false;
    private Measure currentRepeatStartBlockMeasure;
    private Measure currentRepeatEndBlockMeasure;
    private Integer currentEndingCount = 0;
    private List<RepeatBlock> currentRepeatBlocks = new ArrayList<>();
    private boolean hasLyrics = false;

    public static Attributes CURRENT_ATTRIBUTES;
    public static String CURRENT_PART_ID;

    public PartBuilder(Part part) {
        this.part = part;
        CURRENT_PART_ID = part.getId();
    }

    public StringBuilder build() throws BuildException {
        List<Measure> measures = part.getMeasures();

        // pre-processing loop
        // check for endings
        // check for vocal part: presence of lyric is indicator
        for(Measure measure : measures) {
            boolean hasEnding = false;

            List<MusicData> musicDataList = measure.getMusicDataList();
            for(MusicData musicData : musicDataList) {
                musicData.setStaffNumber(measure.getStaffNumber());

                if(musicData instanceof Note) {
                    Note note = (Note)musicData;
                    FullNote fullNote = note.getFullNote();

                    if(skipNote(note)) {
                        continue;
                    }

                    if (!note.getLyrics().isEmpty()) {
                        hasLyrics = true;
                    }

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
                    Tuplet tuplet = note.getTuplet();
                    if(tuplet != null) {
                        Connection tupletType = tuplet.getType();
                        switch (tupletType) {
                            case START:
                                note.setTupletType(Connection.START);
                                tupletsOn = true;
                                break;
                            case STOP:
                                note.setTupletType(Connection.STOP);
                                tupletsOn = false;
                                break;
                        }
                    } else if(note.getFullNote().isChord() && previousNote.getFullNote().isChord() && previousNote.getTupletType() == Connection.STOP) {
                        // adjust end tuplet on chords
                        previousNote.setTupletType(Connection.CONTINUE);
                        note.setTupletType(Connection.STOP);
                    } else if(tupletsOn) {
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

                        for(RepeatBlock repeatBlock : currentRepeatBlocks) {
                            repeatBlock.setEndingCount(currentEndingCount);
                        }
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
                    currentRepeatStartBlockMeasure = null;
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

        if (hasLyrics) {
            append("\\new Voice = \"");
            append(part.getId());
            appendLine("\" {");
        } else {
            appendLine("{");
        }

        // main processing loop
        for(Measure measure : measures) {
            try {
                MeasureBuilder measureBuilder = new MeasureBuilder(measure);
                append(measureBuilder.build().toString());
            } catch (BuildException e) {
                System.err.println(e.getMessage());
            }
        }

        appendLine("}");

        // build lyrics block, if there are any
        if(hasLyrics) {
            LyricPartBuilder lyricPartBuilder = new LyricPartBuilder(part);
            append(lyricPartBuilder.build().toString());
        }

        CURRENT_ATTRIBUTES = null;

        return stringBuilder;
    }

    public static boolean skipNote(Note note) {
        // skip cues and non-printed chords as redundant
        return note.getCue() || (note.getFullNote().isChord() && !note.getPrintout().getPrintObject());
    }
}
