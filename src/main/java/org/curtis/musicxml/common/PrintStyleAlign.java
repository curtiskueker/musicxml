package org.curtis.musicxml.common;

public class PrintStyleAlign {
    private PrintStyle printStyle;
    private Location halign;
    private Location valign;

    public PrintStyleAlign() {

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
