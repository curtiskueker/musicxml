package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.EnclosureShapeConverter;
import org.curtis.musicxml.converter.HalignConverter;
import org.curtis.musicxml.converter.TextDirectionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "text_format")
public class TextFormat extends DatabaseItem {
    @Column
    private String value;
    @Convert(converter = HalignConverter.class)
    @Column
    private Halign justify;
    @Column
    private Integer underline;
    @Column
    private Integer overline;
    @Column(name = "line_through")
    private Integer lineThrough;
    @Column(name = "text_rotation", precision = 12, scale = 4)
    private BigDecimal textRotation;
    @Column(name = "letter_spacing")
    private String letterSpacing;
    @Column(name = "line_height")
    private String lineHeight;
    @Column(name = "lang")
    private String lang;
    @Column(name = "space")
    private String space;
    @Convert(converter = TextDirectionConverter.class)
    @Column(name = "text_direction")
    private TextDirection textDirection;
    @Convert(converter = EnclosureShapeConverter.class)
    @Column
    private EnclosureShape enclosure;

    public TextFormat() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Halign getJustify() {
        return justify;
    }

    public void setJustify(Halign justify) {
        this.justify = justify;
    }

    public Integer getUnderline() {
        return underline;
    }

    public void setUnderline(Integer underline) {
        this.underline = underline;
    }

    public Integer getOverline() {
        return overline;
    }

    public void setOverline(Integer overline) {
        this.overline = overline;
    }

    public Integer getLineThrough() {
        return lineThrough;
    }

    public void setLineThrough(Integer lineThrough) {
        this.lineThrough = lineThrough;
    }

    public BigDecimal getTextRotation() {
        return textRotation;
    }

    public void setTextRotation(BigDecimal textRotation) {
        this.textRotation = textRotation;
    }

    public String getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(String letterSpacing) {
        this.letterSpacing = letterSpacing;
    }

    public String getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(String lineHeight) {
        this.lineHeight = lineHeight;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public TextDirection getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(TextDirection textDirection) {
        this.textDirection = textDirection;
    }

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
