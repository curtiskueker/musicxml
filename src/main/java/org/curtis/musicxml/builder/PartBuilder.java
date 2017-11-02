package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;

import java.math.BigDecimal;
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
            append(measureBuilder.build().toString());
        }

        appendLine("}");

        return stringBuilder;
    }
}
