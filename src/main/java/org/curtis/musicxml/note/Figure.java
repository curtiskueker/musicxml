package org.curtis.musicxml.note;

import org.curtis.musicxml.common.StyleText;
import org.curtis.musicxml.note.lyric.Extend;

public class Figure {
    private StyleText prefix;
    private StyleText figureNumber;
    private StyleText suffix;
    private Extend extend;

    public Figure() {

    }

    public StyleText getPrefix() {
        return prefix;
    }

    public void setPrefix(StyleText prefix) {
        this.prefix = prefix;
    }

    public StyleText getFigureNumber() {
        return figureNumber;
    }

    public void setFigureNumber(StyleText figureNumber) {
        this.figureNumber = figureNumber;
    }

    public StyleText getSuffix() {
        return suffix;
    }

    public void setSuffix(StyleText suffix) {
        this.suffix = suffix;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }
}
