package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.note.lyric.Extend;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "figure")
public class Figure extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prefix_id")
    private FigurePart prefix;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "figure_number_id")
    private FigurePart figureNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "suffix_id")
    private FigurePart suffix;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "extend_id")
    private Extend extend;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    public Figure() {

    }

    public FigurePart getPrefix() {
        return prefix;
    }

    public void setPrefix(FigurePart prefix) {
        this.prefix = prefix;
    }

    public FigurePart getFigureNumber() {
        return figureNumber;
    }

    public void setFigureNumber(FigurePart figureNumber) {
        this.figureNumber = figureNumber;
    }

    public FigurePart getSuffix() {
        return suffix;
    }

    public void setSuffix(FigurePart suffix) {
        this.suffix = suffix;
    }

    public Extend getExtend() {
        return extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
