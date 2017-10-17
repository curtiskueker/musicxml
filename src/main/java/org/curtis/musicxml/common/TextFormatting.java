package org.curtis.musicxml.common;

import java.math.BigDecimal;

public class TextFormatting {
    private Location justify;
    private PrintStyleAlign printStyleAlign;
    private TextDecoration textDecoration;
    private BigDecimal textRotation;
    private String letterSpacing;
    private String lineHeight;
    private String lang;
    private String space;
    private Location textDirection;
    private EnclosureShape enclosure;

    public TextFormatting() {

    }

    public Location getJustify() {
        return justify;
    }

    public void setJustify(Location justify) {
        this.justify = justify;
    }

    public PrintStyleAlign getPrintStyleAlign() {
        return printStyleAlign;
    }

    public void setPrintStyleAlign(PrintStyleAlign printStyleAlign) {
        this.printStyleAlign = printStyleAlign;
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
