package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.MeasureLayout;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Entity
@DiscriminatorValue("print")
public class Print extends MusicData {
    @Transient
    private Layout layout;
    @Transient
    private MeasureLayout measureLayout;
    @Transient
    private List<MeasureNumbering> measureNumberings;
    @Transient
    private NameDisplay partNameDisplay;
    @Transient
    private NameDisplay partAbbreviationDisplay;
    @Transient
    private BigDecimal staffSpacing;
    @Transient
    private Boolean newSystem = false;
    @Transient
    private Boolean newPage = false;
    @Transient
    private Integer blankPage;
    @Transient
    private String pageNumber;

    public Print() {

    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public MeasureLayout getMeasureLayout() {
        return measureLayout;
    }

    public void setMeasureLayout(MeasureLayout measureLayout) {
        this.measureLayout = measureLayout;
    }

    public List<MeasureNumbering> getMeasureNumberings() {
        return measureNumberings;
    }

    public void setMeasureNumberings(List<MeasureNumbering> measureNumberings) {
        this.measureNumberings = measureNumberings;
    }

    public NameDisplay getPartNameDisplay() {
        return partNameDisplay;
    }

    public void setPartNameDisplay(NameDisplay partNameDisplay) {
        this.partNameDisplay = partNameDisplay;
    }

    public NameDisplay getPartAbbreviationDisplay() {
        return partAbbreviationDisplay;
    }

    public void setPartAbbreviationDisplay(NameDisplay partAbbreviationDisplay) {
        this.partAbbreviationDisplay = partAbbreviationDisplay;
    }

    public BigDecimal getStaffSpacing() {
        return staffSpacing;
    }

    public void setStaffSpacing(BigDecimal staffSpacing) {
        this.staffSpacing = staffSpacing;
    }

    public Boolean getNewSystem() {
        return newSystem;
    }

    public void setNewSystem(Boolean newSystem) {
        this.newSystem = newSystem;
    }

    public Boolean getNewPage() {
        return newPage;
    }

    public void setNewPage(Boolean newPage) {
        this.newPage = newPage;
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
