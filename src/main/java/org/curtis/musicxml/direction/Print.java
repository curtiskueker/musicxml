package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.MeasureLayout;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("print")
public class Print extends MusicData {
    @Transient
    private Layout layout;
    @Transient
    private MeasureLayout measureLayout;
    @Enumerated(EnumType.STRING)
    @Column(name = "measure_numbering_value")
    private MeasureNumberingType measureNumberingValue;
    @Transient
    private PrintStyleAlign printStyleAlign;
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
    @Column(name = "blank_page")
    private Integer blankPage;
    @Column(name = "page_number")
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

    public MeasureNumberingType getMeasureNumberingValue() {
        return measureNumberingValue;
    }

    public void setMeasureNumberingValue(MeasureNumberingType measureNumberingValue) {
        this.measureNumberingValue = measureNumberingValue;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
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
