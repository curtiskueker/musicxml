package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.Forward;

public class ForwardBuilder extends BaseBuilder {
    private Forward forward;

    public ForwardBuilder(Forward forward) {
        this.forward = forward;
    }

    public StringBuilder build() {
        if (forward == null) return stringBuilder;

        appendLine("<forward>");
        buildElementWithValue("duration", BuilderUtil.stringValue(forward.getDuration()));
        buildEditorialVoice(forward.getEditorialVoice());
        buildElementWithValue("staff", forward.getStaff());
        appendLine("</forward>");


        return stringBuilder;
    }
}
