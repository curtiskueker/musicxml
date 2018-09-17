package org.curtis.musicxml.bin;

import org.curtis.exception.FileException;
import org.curtis.lilypond.ScoreBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;
import org.curtis.xml.XmlException;

public class MusicXml2Ly {
    public static String LILYPOND_VERSION = "2.18.2";

    private void execute(String xmlFilename, String outputFilename) throws MusicXmlException {
        try {
            // output file
            outputFilename += ".ly";

            ScoreHandler scoreHandler = MusicXmlUtil.handleXmlScoreFile(xmlFilename);

            // build the score
            ScoreBuilder scoreBuilder = new ScoreBuilder(scoreHandler.getScore());
            StringBuilder results = scoreBuilder.build();

            FileUtil.stringToFile(results.toString(), outputFilename);
        } catch (XmlException | FileException | BuildException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String args[]) {
        String xmlFilename = args[0];
        String outputfilename = args[1];

        if (xmlFilename.equals("EMPTY") || outputfilename.equals("EMPTY")) {
            System.err.println("Usage: mysicXml2Ly -f inputfile -o outputfile");
            return;
        }
        for (String arg : args) {
            if (arg.equals("DEBUG")) MusicXmlUtil.DEBUG = true;
        }

        MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
        try {
            musicXml2Ly.execute(xmlFilename, outputfilename);
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        }
    }
}
