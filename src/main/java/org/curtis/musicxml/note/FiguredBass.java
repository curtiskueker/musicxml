package org.curtis.musicxml.note;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class FiguredBass extends MusicData {
    private List<Figure> figures;
    private BigDecimal duration;
    // TODO: editorial
    private PrintStyle printStyle;
    // TODO: printout
    private Boolean parentheses;

    public FiguredBass() {

    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }
}
