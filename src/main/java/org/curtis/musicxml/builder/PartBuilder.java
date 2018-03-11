package org.curtis.musicxml.builder;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;

public class PartBuilder extends BaseBuilder {
    private Part part;

    public PartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() {
        append("<part");
        buildAttribute("id", part.getPartId());
        appendLine(">");
        for (Measure measure : part.getMeasures()) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);
            append(measureBuilder.build().toString());
        }
        appendLine("</part>");

        return stringBuilder;
    }
}
