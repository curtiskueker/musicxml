package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;

import java.io.File;

public class MusicXml2Pdf extends MusicXmlScript {
    public MusicXml2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            setSkipComments(true);
            convertLilypondToPdf(getLilypondFromScore(handleXmlScoreFile(new File(getInputFile())).getScore()));
        } catch (Exception e) {
            throw new MusicXmlException(e);
        }
    }
}
