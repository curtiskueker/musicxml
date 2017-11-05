package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;

import java.util.List;

public class PartBuilder extends AbstractBuilder {
    private Part part;

    public PartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() {
        appendLine("{");

        List<Measure> measures = part.getMeasures();
        for(Measure measure : measures) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);
            measureBuilder.setValues(getCurrentTimeSignature());
            append(measureBuilder.build().toString());
            setValues(measureBuilder.getCurrentTimeSignature());
        }

        appendLine("}");

        return stringBuilder;
    }
}
