package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Time;
import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.lyric.Lyric;
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
    private Measure currentRepeatStartBlockMeasure;
    private Measure currentRepeatEndBlockMeasure;
    private Integer currentEndingCount = 0;
    private List<RepeatBlock> currentRepeatBlocks = new ArrayList<>();
    private List<Lyric> lyrics = new ArrayList<>();

    public static Attributes CURRENT_ATTRIBUTES;
    public static String CURRENT_PART_ID;

    public PartBuilder(Part part) {
        this.part = part;
        CURRENT_PART_ID = part.getId();
    }

    public static TimeSignature getCurrentTimeSignature() {
        if(CURRENT_ATTRIBUTES == null) return null;

        List<Time> timeList = CURRENT_ATTRIBUTES.getTimeList();
        for(Time time : timeList) {
            List<TimeSignature> timeSignatures = time.getTimeSignatures();
            if(!timeSignatures.isEmpty()) return timeSignatures.get(0);
        }

        return null;
    }

    public StringBuilder build() throws BuildException {
        List<Measure> measures = part.getMeasures();

        // pro-processing loop
        // check for endings
        // check for vocal part: presence of lyric is indicator
        for(Measure measure : measures) {
            boolean hasEnding = false;

            List<MusicData> musicDataList = measure.getMusicDataList();
            for(MusicData musicData : musicDataList) {
                if(musicData instanceof Barline) {
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
                } else if(musicData instanceof Note) {
                    Note note = (Note)musicData;
                    lyrics.addAll(note.getLyrics());
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

            previousMeasure = measure;
        }

        if (lyrics.isEmpty()) {
            appendLine("{");
        } else {
            append("\\new Voice = \"");
            append(part.getId());
            appendLine("\" {");
        }

        // main processing loop
        for(Measure measure : measures) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);
            append(measureBuilder.build().toString());
        }

        appendLine("}");

        // build lyrics block, if there are any
        if(!lyrics.isEmpty()) {
            append("\\new Lyrics \\lyricsto \"");
            append(part.getId());
            appendLine("\" {");

            for(Lyric lyric : lyrics) {
                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(lyric);
                append(musicDataBuilder.build().toString());
            }

            appendLine("");
            appendLine("}");
        }

        CURRENT_ATTRIBUTES = null;

        return stringBuilder;
    }
}
