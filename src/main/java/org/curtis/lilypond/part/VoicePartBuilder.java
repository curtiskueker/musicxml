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
        appendLine("{");

        for(Measure measure : part.getMeasures()) {
            try {
                MeasureBuilder measureBuilder = new MeasureBuilder(measure);
                append(measureBuilder.build().toString());
            } catch (BuildException e) {
                System.err.println(e.getMessage());
            }
        }

        appendLine("}");

        return stringBuilder;
    }
}
