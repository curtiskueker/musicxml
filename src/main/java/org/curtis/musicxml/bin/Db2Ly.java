package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

public class Db2Ly extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        if (StringUtil.isEmpty(getOutputFile())) throw new MusicXmlException("Empty output filename");
        if (!isStdOut() && !FileUtil.isLyFileExtension(getOutputFile())) setOutputFile(getOutputFile() + ".ly");

        setSkipComments(true);
        outputLilypondResultsToFile(getScoreFromDb());
    }

    public static void main(String[] args) {
        Db2Ly db2Ly = new Db2Ly();
        db2Ly.executeScript(args);
    }
}
