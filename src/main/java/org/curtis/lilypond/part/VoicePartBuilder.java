package org.curtis.lilypond.part;

import org.curtis.lilypond.MeasureBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;

public class VoicePartBuilder extends FilteredPartBuilder {
    private Part part;

    public VoicePartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        if (voices.isEmpty()) voices.add("1");
        String defaultVoice = voices.first();
        boolean hasMultipleVoices = voices.size() > 1;

        appendLine("{");

        for (String voice : voices) {
            if (hasMultipleVoices && voice.equals(voices.first())) {
                appendLine();
                appendLine("<<");
                appendLine("{");
            }

            for(Measure measure : part.getMeasures()) {
                try {
                    MeasureBuilder measureBuilder = new MeasureBuilder(measure, voice, defaultVoice);
                    append(measureBuilder.build().toString());
                } catch (BuildException e) {
                    System.err.println(e.getMessage());
                }
            }

            if (hasMultipleVoices) {
                if (voice.equals(voices.last())) {
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

            PartBuilder.CURRENT_ATTRIBUTES = null;
        }

        appendLine("}");

        return stringBuilder;
    }
}
