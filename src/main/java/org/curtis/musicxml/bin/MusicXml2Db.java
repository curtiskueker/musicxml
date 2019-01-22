package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.PartItem;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlException;

import java.io.File;

public class MusicXml2Db extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        try {
            DBTransaction dbTransaction = MusicXmlUtil.getDbTransaction();

            String scoreName = StringUtil.isEmpty(getScoreName()) ? getInputFile() : getScoreName();
            if (dbTransaction.find(Score.class, "scoreName", scoreName) != null) throw new MusicXmlException("Score name " + scoreName + " already exists");

            File inputFile = new File(getInputFile());
            ScoreHandler scoreHandler = handleXmlScoreFile(inputFile);
            Score score = scoreHandler.getScore();
            score.setScoreName(scoreName);

            System.err.println("Creating database record...");
            Integer partItemOrdering = 1;
            for (PartItem partItem : score.getScoreHeader().getPartList().getPartItems()) {
                partItem.setOrdering(partItemOrdering);
                partItemOrdering++;
            }

            Integer partOrdering = 1;
            for (Part part : score.getParts()) {
                part.setOrdering(partOrdering);
                partOrdering++;
                Integer measureOrdering = 1;
                for (Measure measure : part.getMeasures()) {
                    measure.setOrdering(measureOrdering);
                    measureOrdering++;
                }
            }

            dbTransaction.create(score);

            // MusicData created separately
            for (Part part : score.getParts()) {
                for (Measure measure : part.getMeasures()) {
                    Integer musicDataOrdering = 1;
                    for (MusicData musicData : measure.getMusicDataList()) {
                        musicData.setMeasure(measure);
                        musicData.setOrdering(musicDataOrdering);
                        musicDataOrdering++;
                        dbTransaction.create(musicData);
                    }
                }
            }

            DBSessionFactory.getInstance().closeTransaction();
        } catch (DBException | XmlException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        }
    }

    public static void main(String[] args) {
        try {
            MusicXml2Db musicXmlDb = new MusicXml2Db();
            musicXmlDb.setArgs(args);
            musicXmlDb.setOutputFile("NONE");
            musicXmlDb.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
