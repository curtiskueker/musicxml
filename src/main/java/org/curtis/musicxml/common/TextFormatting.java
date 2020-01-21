package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "text_formatting")
public class TextFormatting extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private Location justify;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_decoration_id")
    private TextDecoration textDecoration;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "text_direction")
    private Location textDirection;
    @Enumerated(EnumType.STRING)
    @Column
    private EnclosureShape enclosure;

    public TextFormatting() {

    }

    public Location getJustify() {
        return justify;
    }

    public void setJustify(Location justify) {
        this.justify = justify;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public TextDecoration getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
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

    public Location getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(Location textDirection) {
        this.textDirection = textDirection;
    }

    public EnclosureShape getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureShape enclosure) {
        this.enclosure = enclosure;
    }
}
