package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Printout;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FiguredBass extends MusicData {
    private List<Figure> figures = new ArrayList<>();
    private BigDecimal duration;
    private Editorial editorial;
    private PrintStyle printStyle;
    private Printout printout;
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

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Printout getPrintout() {
        return printout;
    }

    public void setPrintout(Printout printout) {
        this.printout = printout;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }
}
