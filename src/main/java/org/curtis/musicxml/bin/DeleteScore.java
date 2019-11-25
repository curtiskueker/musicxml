package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;

public class DeleteScore extends MusicXmlScript {
    public DeleteScore() {

    }

    public void execute() throws MusicXmlException {
        Score score = getScoreFromDb();

        try {
            System.err.println("Deleting score " + score.getScoreName() + "...");
            DBTransaction dbTransaction = MusicXmlUtil.getDbTransaction();
            dbTransaction.delete(score);
            System.err.println("Score " + score.getScoreName() + " deleted.");

            MusicXmlUtil.getNewDbTransaction();
        } catch (DBException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        } finally {
            try {
                DBSessionFactory.getInstance().closeTransaction();
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

    }
}
