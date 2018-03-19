package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.note.Forward;

public class ForwardBuilder extends BaseBuilder {
    private Forward forward;

    public ForwardBuilder(Forward forward) {
        this.forward = forward;
    }

    public StringBuilder build() {
        if (forward == null) return stringBuilder;

        // TODO: forward duration
        appendLine("<forward>");
        buildElementWithValue("duration", 1);
        appendLine("</forward>");


        return stringBuilder;
    }
}
