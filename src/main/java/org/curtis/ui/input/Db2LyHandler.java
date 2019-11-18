package org.curtis.ui.input;

import org.curtis.musicxml.bin.Db2Ly;
import org.curtis.ui.input.conversion.FromDbInput;
import org.curtis.ui.input.conversion.ToLyInput;

public class Db2LyHandler extends ConversionHandler {
    public Db2LyHandler() {
        musicXmlScript = new Db2Ly();
        fromInput = new FromDbInput();
        toInput = new ToLyInput();
    }
}
