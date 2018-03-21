package org.curtis.lilypond.part;

import org.curtis.lilypond.MeasureBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.RepeatBlock;
import org.curtis.musicxml.score.RepeatBlockType;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class VoicePartBuilder extends FilteredPartBuilder {
    private Part part;
    private int currentVoiceCount = 0;
    private List<Measure> currentMeasureList = new ArrayList<>();

    public VoicePartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        appendLine("{");

        for(Measure measure : part.getMeasures()) {
            try {
                if (isVoiceCountChange(measure) || isStartRepeatBlock(measure)) {
                    processMeasures();
                }

                currentMeasureList.add(measure);
                currentVoiceCount = measure.getVoices().size();

                if (isEndRepeatBlock(measure)) {
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

    private boolean isVoiceCountChange(Measure measure) {
        int measureVoiceCount = measure.getVoices().size();

        return currentVoiceCount > 0 && measureVoiceCount != currentVoiceCount;
    }

    private boolean isStartRepeatBlock(Measure measure) {
        RepeatBlock repeatBlock = measure.getRepeatBlock();
        return repeatBlock != null && (repeatBlock.getConnectionType() == Connection.START || repeatBlock.getConnectionType() == Connection.SINGLE);
    }

    private boolean isEndRepeatBlock(Measure measure) {
        RepeatBlock repeatBlock = measure.getRepeatBlock();
        return repeatBlock != null && (repeatBlock.getConnectionType() == Connection.STOP || repeatBlock.getConnectionType() == Connection.SINGLE);
    }

    private boolean isMainRepeatBlock(Measure measure) {
        RepeatBlock repeatBlock = measure.getRepeatBlock();
        return repeatBlock !=  null && repeatBlock.getRepeatBlockType() == RepeatBlockType.MAIN;
    }

    private boolean isEndingRepeatBlock(Measure measure) {
        RepeatBlock repeatBlock = measure.getRepeatBlock();
        return repeatBlock !=  null && repeatBlock.getRepeatBlockType() == RepeatBlockType.ENDING;
    }

    private void processMeasures() throws BuildException {
        if (currentMeasureList.isEmpty()) return;

        SortedSet<String> measureVoices = currentMeasureList.get(0).getVoices();
        // TODO: process when no voice elements are found
        if (measureVoices.isEmpty()) return;

        boolean hasMultipleVoices = measureVoices.size() > 1;
        String defaultVoice = measureVoices.first();

        Attributes currentAttributes = AttributesUtil.attributesCopy(PartBuilder.CURRENT_ATTRIBUTES);
        for (String voice : measureVoices) {
            // Reset attributes state at the beginning of each voice
            AttributesUtil.setCurrentAttributes(currentAttributes);

            // Begin repeat endings
            Measure firstMeasure = currentMeasureList.get(0);
            if (isMainRepeatBlock(firstMeasure) && isStartRepeatBlock(firstMeasure) && voice.equals(measureVoices.first())) {
                append("\\repeat volta #");
                append(String.valueOf(firstMeasure.getRepeatBlock().getEndingCount()));
                appendLine(" {");
            } else if(isEndingRepeatBlock(firstMeasure) && isStartRepeatBlock(firstMeasure)) {
                if (firstMeasure.getRepeatBlock().getEndingNumber().equals(1)) {
                    appendLine("\\alternative {");
                }
                appendLine("{");
            }

            if (hasMultipleVoices && voice.equals(measureVoices.first())) {
                appendLine();
                appendLine("<<");
                appendLine("{");
            }

            for (Measure measure : currentMeasureList) {
                MeasureBuilder measureBuilder = new MeasureBuilder(measure, voice, defaultVoice);
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
            Measure lastMeasure = currentMeasureList.get(currentMeasureList.size() - 1);
            if (isEndRepeatBlock(lastMeasure)) {
                appendLine();
                appendLine("}");

                RepeatBlock repeatBlock = lastMeasure.getRepeatBlock();
                if (isEndingRepeatBlock(lastMeasure) && repeatBlock.getEndingNumber().equals(repeatBlock.getEndingCount())) {
                    appendLine("}");
                }
            }
        }

        currentMeasureList.clear();
    }
}
