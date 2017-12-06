package org.curtis.lilypond.builder.musicdata;

import org.curtis.musicxml.direction.Print;

public class PrintBuilder extends MusicDataBuilder {
    private Print print;

    public PrintBuilder(Print print) {
        super(print);
        this.print = print;
    }

    public StringBuilder build() {
        if(print.getNewSystem()) {
            appendLine("\\break");
        } else if(print.getNewPage()) {
            appendLine("\\pageBreak");
        }

        return stringBuilder;
    }
}
