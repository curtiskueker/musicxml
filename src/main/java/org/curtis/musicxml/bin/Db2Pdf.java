package org.curtis.musicxml.bin;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.exception.MusicXmlException;

public class Db2Pdf extends MusicXmlScript {
    public Db2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            SKIP_COMMENTS = true;
            convertLilypondToPdf(getLilypondFromScore(getScoreFromDb()));
        } catch (BuildException e) {
            throw new MusicXmlException(e);
        }
    }
}
