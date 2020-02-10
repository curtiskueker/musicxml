package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.note.Forward;

public class ForwardBuilder extends MusicDataBuilder {
    private Forward forward;

    public ForwardBuilder(Forward forward) {
        this.forward = forward;
    }

    public StringBuilder build() {
        if (forward == null) return stringBuilder;

        buildStartElement("forward");
        buildElementWithValue("duration", forward.getDuration());
        buildEditorial(forward.getEditorial());
        buildElementWithValue("staff", forward.getStaff());
        buildEndElement("forward");


        return stringBuilder;
    }
}
