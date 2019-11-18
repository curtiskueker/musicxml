package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXml2Pdf;
import org.curtis.ui.input.conversion.FromMusicXmlInput;
import org.curtis.ui.input.conversion.ToPdfInput;

public class MusicXml2PdfHandler extends ConversionHandler {
    public MusicXml2PdfHandler() {
        musicXmlScript = new MusicXml2Pdf();
        fromInput = new FromMusicXmlInput();
        toInput = new ToPdfInput();
    }
}
