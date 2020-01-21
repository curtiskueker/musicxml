package org.curtis.musicxml.note.lyric;

import org.apache.commons.text.StringEscapeUtils;
import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.TextDecoration;
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
@Table(name = "text_data")
public class TextData extends DatabaseItem {
    @Column
    private String value;
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
    @Column
    private String lang;
    @Enumerated(EnumType.STRING)
    @Column(name = "text_direction")
    private Location textDirection;

    public TextData() {

    }

    public String getValue() {
        return StringEscapeUtils.unescapeXml(value);
    }

    public void setValue(String value) {
        this.value = value;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Location getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(Location textDirection) {
        this.textDirection = textDirection;
    }
}
