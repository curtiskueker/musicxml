package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.xml.XmlException;

import java.io.File;

public class MusicXml2Ly extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        try {
            // output file
            if (!OUTPUT_FILE.endsWith(".ly")) OUTPUT_FILE += ".ly";

            File xmlFile = new File(INPUT_FILE);
            SKIP_COMMENTS = true;
            ScoreHandler scoreHandler = handleXmlScoreFile(xmlFile);
            outputLilypondResultsToFile(scoreHandler.getScore());
        } catch (XmlException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
            musicXml2Ly.setArgs(args);
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
