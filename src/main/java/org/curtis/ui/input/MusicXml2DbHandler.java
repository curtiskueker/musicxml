package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXml2Db;
import org.curtis.ui.input.conversion.FromMusicXmlInput;
import org.curtis.ui.input.conversion.ToDbInput;

public class MusicXml2DbHandler extends ConversionHandler {
    public MusicXml2DbHandler() {
        musicXmlScript = new MusicXml2Db();
        fromInput = new FromMusicXmlInput();
        toInput = new ToDbInput();
    }
}
