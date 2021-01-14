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
        try {
            Ly2Pdf ly2Pdf = new Ly2Pdf();
            ly2Pdf.setArgs(args);
            ly2Pdf.execute();
        } catch (MusicXmlException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Throwable e){
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
