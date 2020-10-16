package org.curtis.lilypond;

import org.curtis.lilypond.part.PartBuilder;
import org.curtis.musicxml.score.Measure;
import org.curtis.util.StringUtil;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public abstract class BaseBuilder {
    protected String getPartAndMeasure(Measure measure) {
        String value = "";
        if (StringUtil.isNotEmpty(PartBuilder.CURRENT_PART_ID)) {
            value += "Part " + PartBuilder.CURRENT_PART_ID;
        }
        if (measure != null) {
            value += " Measure " + measure.getNumber() + ": ";
        }

        return value;
    }

    protected void displayMeasureMessage(Measure measure, String message) {
        System.err.println(getPartAndMeasure(measure) + message);
    }

    protected void displayException(Exception e) {
        if (StringUtil.isEmpty(e.getMessage())) {
            System.err.println("Exception: no message");
        } else {
            System.err.println("Exception: " + e.getMessage());
        }
        if (!DEBUG) return;

        System.err.println();
    }
}
