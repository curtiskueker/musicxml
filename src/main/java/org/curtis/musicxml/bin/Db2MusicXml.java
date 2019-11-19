package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.util.StringUtil;

public class Db2MusicXml extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        if (StringUtil.isEmpty(getOutputFile())) throw new MusicXmlException("Empty output filename");
        outputResultsToFile(getXmlResults(getScoreFromDb()));
    }

    public static void main(String[] args) {
        try {
            Db2MusicXml db2MusicXml = new Db2MusicXml();
            db2MusicXml.setArgs(args);
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
