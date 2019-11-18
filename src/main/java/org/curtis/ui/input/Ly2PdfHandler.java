package org.curtis.ui.input;

import org.curtis.musicxml.bin.Ly2Pdf;
import org.curtis.ui.input.conversion.FromLyInput;
import org.curtis.ui.input.conversion.ToPdfInput;

public class Ly2PdfHandler extends ConversionHandler {
    public Ly2PdfHandler() {
        musicXmlScript = new Ly2Pdf();
        fromInput = new FromLyInput();
        toInput = new ToPdfInput();
    }
}
