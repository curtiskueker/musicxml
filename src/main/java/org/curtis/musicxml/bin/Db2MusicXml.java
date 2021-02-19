package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.CompressedXmlUtil;

public class Db2MusicXml extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        if (StringUtil.isEmpty(getOutputFile())) throw new MusicXmlException("Empty output filename");
        if (CompressedXmlUtil.isCompressedFile(getOutputFile()) && !FileUtil.isXmlFileExtension(getZippedFile()))
            throw new MusicXmlException("Zipped filename must have .xml or .musicxml extension");
        else if (!isStdOut() && !FileUtil.isMusicXmlFileExtension(getOutputFile())) setOutputFile(getOutputFile() + ".xml");

        Score score = getScoreFromDb();
        String results = getXmlResults(score);
        outputResultsToFile(results, score.getEncoding());
    }

    public static void main(String[] args) {
        try {
            Db2MusicXml db2MusicXml = new Db2MusicXml();
            db2MusicXml.setArgs(args);
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Throwable e){
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
