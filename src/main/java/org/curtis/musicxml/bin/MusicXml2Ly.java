package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.xml.XmlException;

import java.io.File;

public class MusicXml2Ly extends MusicXmlScript {
    public static String LILYPOND_VERSION = "2.18.2";

    private void execute() throws MusicXmlException {
        try {
            // output file
            OUTPUT_FILE += ".ly";

            File xmlFile = new File(FILENAME);
            ScoreHandler scoreHandler = MusicXmlUtil.handleXmlScoreFile(xmlFile);
            outputScore(scoreHandler.getScore());
        } catch (XmlException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            setArgs(args);

            MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
            musicXml2Ly.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
