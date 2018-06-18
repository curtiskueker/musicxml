package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "font")
public class Font extends DatabaseItem {
    @Column(name = "font_family")
    private String fontFamily;
    @Enumerated(EnumType.STRING)
    @Column(name = "font_style")
    private FontStyle fontStyle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "font_size_id")
    private FontSize fontSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "font_weight")
    private FontWeight fontWeight;

    public Font() {

    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }
}
