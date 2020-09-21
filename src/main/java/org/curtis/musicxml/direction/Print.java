package org.curtis.musicxml.direction;

import org.curtis.musicxml.converter.MeasureNumberingTypeConverter;
import org.curtis.musicxml.display.NameDisplay;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.score.MusicDataElement;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("print")
public class Print extends MusicDataElement {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "layout_id")
    private Layout layout;
    @Column(name = "measure_distance", precision = 12, scale = 4)
    private BigDecimal measureDistance;
    @Convert(converter = MeasureNumberingTypeConverter.class)
    @Column(name = "measure_numbering_value")
    private MeasureNumberingType measureNumberingValue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display measureNumberingDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_name_display_id")
    private NameDisplay partNameDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_abbreviation_display_id")
    private NameDisplay partAbbreviationDisplay;
    @Column(name = "staff_spacing", precision = 12, scale = 4)
    private BigDecimal staffSpacing;
    @Column(name = "new_system")
    @Type(type="yes_no")
    private Boolean newSystem = false;
    @Column(name = "new_page")
    @Type(type="yes_no")
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

    public BigDecimal getMeasureDistance() {
        return measureDistance;
    }

    public void setMeasureDistance(BigDecimal measureDistance) {
        this.measureDistance = measureDistance;
    }

    public MeasureNumberingType getMeasureNumberingValue() {
        return measureNumberingValue;
    }

    public void setMeasureNumberingValue(MeasureNumberingType measureNumberingValue) {
        this.measureNumberingValue = measureNumberingValue;
    }

    public Display getMeasureNumberingDisplay() {
        return measureNumberingDisplay;
    }

    public void setMeasureNumberingDisplay(Display measureNumberingDisplay) {
        this.measureNumberingDisplay = measureNumberingDisplay;
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
