package org.curtis.musicxml.direction;

import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class Print extends MusicData {
    // TODO: layout
    // TODO: measure layout
    private List<MeasureNumbering> measureNumberings;
    // TODO: part name display
    // TODO: part abbreviation display
    // TODO: staff spacing
    // TODO: new system
    // TODO: new page
    private Integer blankPage;
    private String pageNumber;

    public Print() {

    }

    public List<MeasureNumbering> getMeasureNumberings() {
        return measureNumberings;
    }

    public void setMeasureNumberings(List<MeasureNumbering> measureNumberings) {
        this.measureNumberings = measureNumberings;
    }

    public Integer getBlankPage() {
        return blankPage;
    }

    public void setBlankPage(Integer blankPage) {
        this.blankPage = blankPage;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }
}
