package org.curtis.lilypond;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Time;
import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.barline.Ending;
import org.curtis.musicxml.barline.Repeat;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.score.Measure;
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

    public static Attributes CURRENT_ATTRIBUTES;

    public PartBuilder(Part part) {
        this.part = part;
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

    public StringBuilder build() {
        appendLine("{");

        List<Measure> measures = part.getMeasures();

        // pro-processing loop
        // check for endings
        for(Measure measure : measures) {
            for(Barline barline : measure.getBarlines()){
                Ending ending = barline.getEnding();
                if(ending == null) {
                    if (previousMeasure != null) {
                        RepeatBlock previousRepeatBlock = previousMeasure.getRepeatBlock();
                        if(previousRepeatBlock != null && previousRepeatBlock.getRepeatBlockType() == RepeatBlockType.ENDING &&
                                (previousRepeatBlock.getConnectionType() == Connection.STOP || previousRepeatBlock.getConnectionType() == Connection.SINGLE)) {
                            currentRepeatStartBlockMeasure = null;
                            currentRepeatEndBlockMeasure = null;
                            currentEndingCount = 0;
                            currentRepeatBlocks.clear();
                        }
                    }
                } else {
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

            previousMeasure = measure;
        }

        // main processing loop
        for(Measure measure : measures) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);
            append(measureBuilder.build().toString());
        }

        appendLine("}");

        CURRENT_ATTRIBUTES = null;

        return stringBuilder;
    }
}
