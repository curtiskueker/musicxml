package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;

import java.io.File;

public class MusicXml2Pdf extends MusicXmlScript {
    public MusicXml2Pdf() {

    }

    public void execute() throws MusicXmlException {
        if (!FileUtil.isMusicXmlFileExtension(getInputFile())) throw new MusicXmlException("Invalid input file extension");

        try {
            setSkipComments(true);
            MusicXmlUtil.INCLUDE_BREAKS = true;
            convertLilypondToPdf(getLilypondFromScore(handleXmlScoreFile(new File(getInputFile())).getScore()));
        } catch (Exception e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        MusicXml2Pdf musicXml2Pdf = new MusicXml2Pdf();
        musicXml2Pdf.executeScript(args);
    }
}
