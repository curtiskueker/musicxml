package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class PartBuilder extends MusicDataBuilder {
    private Part part;

    public PartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() {
        String partId = part.getPartId();
        if (DEBUG) System.err.println("Part " + partId);
        buildOpenElement("part");
        buildAttribute("id", partId);
        buildCloseElement();
        for (Measure measure : part.getMeasures()) {
            MeasureBuilder measureBuilder = new MeasureBuilder(measure);
            append(measureBuilder.build().toString());
        }
        buildEndElement("part");

        return stringBuilder;
    }
}
