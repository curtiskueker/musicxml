package org.curtis.ui.input;

import org.curtis.musicxml.bin.Db2Pdf;
import org.curtis.ui.input.conversion.FromDbInput;
import org.curtis.ui.input.conversion.ToPdfInput;

public class Db2PdfHandler extends ConversionHandler {
    public Db2PdfHandler() {
        musicXmlScript = new Db2Pdf();
        fromInput = new FromDbInput();
        toInput = new ToPdfInput();
    }
}
