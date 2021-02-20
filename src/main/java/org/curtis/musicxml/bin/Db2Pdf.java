package org.curtis.musicxml.bin;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;

public class Db2Pdf extends MusicXmlScript {
    public Db2Pdf() {

    }

    public void execute() throws MusicXmlException {
        try {
            setSkipComments(true);
            MusicXmlUtil.INCLUDE_BREAKS = true;
            convertLilypondToPdf(getLilypondFromScore(getScoreFromDb()));
        } catch (BuildException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Db2Pdf db2Pdf = new Db2Pdf();
        db2Pdf.executeScript(args);
    }
}
