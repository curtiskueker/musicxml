package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;

public class Ly2Pdf extends MusicXmlScript {
    public Ly2Pdf() {

    }

    public void execute() throws MusicXmlException {
        MusicXmlUtil.INCLUDE_BREAKS = true;
        convertLilypondToPdf();
    }

    public static void main(String[] args) {
        Ly2Pdf ly2Pdf = new Ly2Pdf();
        ly2Pdf.executeScript(args);
    }
}
