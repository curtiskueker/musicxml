package org.curtis.musicxml.handler;

import org.w3c.dom.Element;

public class ScoreHandler extends AbstractHandler {
    public ScoreHandler(Element element) {
        super(element);
    }

    public StringBuilder handle() {
        StringBuilder scoreStringBuilder = getStringBuilder();

        // begin score
        scoreStringBuilder.append("\\score {\n");

        // temporary output
        scoreStringBuilder.append("{c' e' g' e'}\n");
        // end score
        scoreStringBuilder.append("}\n");

        return scoreStringBuilder;
    }
}
