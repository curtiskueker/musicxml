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
        append("{");

        // TODO: use separate MesaureBuilder
        List<Measure> measures = part.getMeasures();
        for(Measure measure : measures) {
            append("\\bar \"|\" ");
        }

        appendLine("}");

        return stringBuilder;
    }
}
