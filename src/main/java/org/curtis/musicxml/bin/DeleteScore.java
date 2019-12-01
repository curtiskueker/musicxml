package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MeasureItem;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;

import java.util.ArrayList;
import java.util.List;

public class DeleteScore extends MusicXmlScript {
    public DeleteScore() {

    }

    public void execute() throws MusicXmlException {
        Score score = getScoreFromDb();

        try {
            System.err.println("Deleting score " + score.getScoreName() + "...");

            // MusicData deleted separately
            System.err.println("Deleting Music Data...");
            DBTransaction dbTransaction = MusicXmlUtil.getNewDbTransaction();
            List<Part> parts = score.getParts();
            for (Part part : parts) {
                System.err.println("Deleting Part " + part.getPartId() + "...");
                List<Measure> measures = part.getMeasures();
                for (Measure measure : measures) {
                    System.err.println("Deleting Measure " + measure.getNumber() + "...");
                    List<MeasureItem> measureItems = measure.getMeasureItems();
                    for (MeasureItem measureItem : measureItems) {
                        MusicData musicData = dbTransaction.getObjectById(MusicData.class, measureItem.getId());
                        dbTransaction.delete(musicData);
                    }
                    dbTransaction.commit();
                    measure.setMeasureItems(new ArrayList<>());
                }
            }
            System.err.println("Music Data deleted...");

            // Delete score
            dbTransaction.delete(score);
            dbTransaction.commit();
            DBSessionFactory.getInstance().closeTransaction();

            System.err.println("Score " + score.getScoreName() + " deleted.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        } finally {
            try {
                MusicXmlUtil.getNewDbTransaction();
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

    }
}
