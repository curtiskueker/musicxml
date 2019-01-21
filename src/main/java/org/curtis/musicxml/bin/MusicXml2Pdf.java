package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;

import java.io.File;

public class MusicXml2Pdf extends MusicXmlScript {
    public MusicXml2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            SKIP_COMMENTS = true;
            convertLilypondToPdf(getLilypondFromScore(handleXmlScoreFile(new File(INPUT_FILE)).getScore()));
        } catch (Exception e) {
            throw new MusicXmlException(e);
        }
    }
}
