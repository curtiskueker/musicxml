package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.note.PlacementText;

public class Bend extends Technical {
    // TODO: bend alter
    // TODO: pre bend
    // TODO: release
    private PlacementText withBar;
    private PrintStyle printStyle;
    // TODO: bend sound

    public Bend() {

    }

    public PlacementText getWithBar() {
        return withBar;
    }

    public void setWithBar(PlacementText withBar) {
        this.withBar = withBar;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }
}
