package org.curtis.ui.input;

import org.curtis.musicxml.bin.Db2MusicXml;
import org.curtis.ui.input.conversion.FromDbInput;
import org.curtis.ui.input.conversion.ToMusicXmlInput;

public class Db2MusicXmlHandler extends ConversionHandler {
    public Db2MusicXmlHandler() {
        musicXmlScript = new Db2MusicXml();
        fromInput = new FromDbInput();
        toInput = new ToMusicXmlInput();
    }
}
