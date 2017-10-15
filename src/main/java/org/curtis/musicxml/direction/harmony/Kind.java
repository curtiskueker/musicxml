package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

public class Kind {
    private String kindValue;
    private Boolean useSymbols;
    private String text;
    private Boolean stackDegrees;
    private Boolean parenthesesDegrees;
    private Boolean bracketDegrees;
    private PrintStyle printStyle;
    private String halign;
    private String valign;

    public Kind() {

    }

    public String getKindValue() {
        return kindValue;
    }

    public void setKindValue(String kindValue) {
        this.kindValue = kindValue;
    }

    public Boolean getUseSymbols() {
        return useSymbols;
    }

    public void setUseSymbols(Boolean useSymbols) {
        this.useSymbols = useSymbols;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getStackDegrees() {
        return stackDegrees;
    }

    public void setStackDegrees(Boolean stackDegrees) {
        this.stackDegrees = stackDegrees;
    }

    public Boolean getParenthesesDegrees() {
        return parenthesesDegrees;
    }

    public void setParenthesesDegrees(Boolean parenthesesDegrees) {
        this.parenthesesDegrees = parenthesesDegrees;
    }

    public Boolean getBracketDegrees() {
        return bracketDegrees;
    }

    public void setBracketDegrees(Boolean bracketDegrees) {
        this.bracketDegrees = bracketDegrees;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public String getHalign() {
        return halign;
    }

    public void setHalign(String halign) {
        this.halign = halign;
    }

    public String getValign() {
        return valign;
    }

    public void setValign(String valign) {
        this.valign = valign;
    }
}
