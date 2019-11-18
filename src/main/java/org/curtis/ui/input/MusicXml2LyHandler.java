package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXml2Ly;
import org.curtis.ui.input.conversion.FromMusicXmlInput;
import org.curtis.ui.input.conversion.ToLyInput;

public class MusicXml2LyHandler extends ConversionHandler {
    public MusicXml2LyHandler() {
        musicXmlScript = new MusicXml2Ly();
        fromInput = new FromMusicXmlInput();
        toInput = new ToLyInput();
    }
}
