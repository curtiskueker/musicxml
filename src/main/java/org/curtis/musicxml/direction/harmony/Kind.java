package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.Location;

public class Kind {
    private KindValue kindValue;
    private Boolean useSymbols;
    private String text;
    private Boolean stackDegrees;
    private Boolean parenthesesDegrees;
    private Boolean bracketDegrees;
    private PrintStyle printStyle;
    private Location halign;
    private Location valign;

    public Kind() {

    }

    public KindValue getKindValue() {
        return kindValue;
    }

    public void setKindValue(KindValue kindValue) {
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

    public Location getHalign() {
        return halign;
    }

    public void setHalign(Location halign) {
        this.halign = halign;
    }

    public Location getValign() {
        return valign;
    }

    public void setValign(Location valign) {
        this.valign = valign;
    }
}
