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
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
