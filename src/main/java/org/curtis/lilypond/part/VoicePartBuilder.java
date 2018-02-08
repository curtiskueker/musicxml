package org.curtis.lilypond.part;

import org.curtis.lilypond.MeasureBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;

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
                int measureVoiceCount = measure.getVoices().size();

                if (currentVoiceCount > 0 && measureVoiceCount != currentVoiceCount) {
                    processMeasures();
                }

                currentMeasureList.add(measure);
                currentVoiceCount = measure.getVoices().size();
            } catch (BuildException e) {
                System.err.println(e.getMessage());
            }
        }

        processMeasures();

        PartBuilder.CURRENT_ATTRIBUTES = null;

        appendLine("}");

        return stringBuilder;
    }

    private void processMeasures() throws BuildException {
        if (currentMeasureList.isEmpty()) return;

        SortedSet<String> measureVoices = currentMeasureList.get(0).getVoices();
        if (measureVoices.isEmpty()) return;

        boolean hasMultipleVoices = measureVoices.size() > 1;
        String defaultVoice = measureVoices.first();

        for (String voice : measureVoices) {
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
        }

        currentMeasureList.clear();
    }
}
