package org.curtis.musicxml.bin;

import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlException;

import java.io.File;

public class MusicXml2Ly extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        try {
            if (StringUtil.isEmpty(getOutputFile())) throw new MusicXmlException("Empty output filename");
            if (!FileUtil.isMusicXmlFileExtension(getInputFile())) throw new MusicXmlException("Invalid input file extension");
            if (!isStdOut() && !FileUtil.isLyFileExtension(getOutputFile())) setOutputFile(getOutputFile() + ".ly");
            File xmlFile = FileUtil.getFile(getInputFile());
            setSkipComments(true);
            ScoreHandler scoreHandler = handleXmlScoreFile(xmlFile);
            outputLilypondResultsToFile(scoreHandler.getScore());
        } catch (XmlException | FileException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
        musicXml2Ly.executeScript(args);
    }
}
