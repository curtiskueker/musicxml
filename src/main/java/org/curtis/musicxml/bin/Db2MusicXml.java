package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;

public class Db2MusicXml {
    public static Integer SCORE_ID;

    private void execute() throws MusicXmlException {
        try {
            Score score = null;
            if (SCORE_ID != null) {
                DBTransaction dbTransaction = MusicXmlUtil.getDbTransaction();
                score = dbTransaction.getObjectById(Score.class, SCORE_ID);
            }
        } catch (DBException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        } finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("SCORE_ID=")) {
                SCORE_ID = Integer.parseInt(arg.replace("SCORE_ID=", ""));
            }
        }
        Db2MusicXml db2MusicXml = new Db2MusicXml();
        try {
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        }
    }
}
