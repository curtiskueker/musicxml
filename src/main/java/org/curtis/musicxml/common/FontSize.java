package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "font_size")
public class FontSize extends DatabaseItem {
    @Column(name = "font_size")
    private BigDecimal fontSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "css_font_size")
    private CssFontSize cssFontSize;

    public FontSize() {

    }

    public BigDecimal getFontSize() {
        return fontSize;
    }

    public void setFontSize(BigDecimal fontSize) {
        this.fontSize = fontSize;
    }

    public CssFontSize getCssFontSize() {
        return cssFontSize;
    }

    public void setCssFontSize(CssFontSize cssFontSize) {
        this.cssFontSize = cssFontSize;
    }
}
