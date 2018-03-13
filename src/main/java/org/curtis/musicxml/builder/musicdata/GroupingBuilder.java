package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.direction.Feature;
import org.curtis.musicxml.direction.Grouping;

public class GroupingBuilder extends BaseBuilder {
    private Grouping grouping;

    public GroupingBuilder(Grouping grouping) {
        this.grouping = grouping;
    }

    public StringBuilder build() {
        if (grouping == null) return stringBuilder;

        append("<grouping");
        buildAttribute("number", grouping.getNumber());
        buildAttribute("number-of", grouping.getNumberOf());
        appendLine(">");
        for (Feature feature : grouping.getFeatures()) buildElementWithValueAndAttribute("feature", feature.getValue(), "type", feature.getType());
        appendLine("</grouping>");

        return stringBuilder;
    }
}
