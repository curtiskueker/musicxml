package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.util.MathUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "font")
public class Font extends DatabaseItem {
    @Column(name = "font_family")
    private String fontFamily;
    @Enumerated(EnumType.STRING)
    @Column(name = "font_style")
    private FontStyle fontStyle;
    @Column(name = "font_size")
    private String fontSize;
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

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public BigDecimal getNumericFontSize() {
        if (fontSize == null) return null;

        try {
            return MathUtil.newBigDecimal(fontSize);
        } catch (Exception e) {
            return null;
        }
    }

    public String getNonNumericFontSize() {
        return BuilderUtil.enumValue(fontSize);
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }
}
