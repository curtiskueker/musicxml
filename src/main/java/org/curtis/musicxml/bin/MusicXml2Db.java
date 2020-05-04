package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.database.ItemOrderer;
import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlException;

import java.io.File;

public class MusicXml2Db extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        File inputFile;
        try {
            inputFile = FileUtil.openFile(getInputFile());
        } catch (FileException e) {
            throw new MusicXmlException(e.getMessage());
        }

        try {
            DBTransaction dbTransaction = MusicXmlUtil.getDbTransaction();

            String scoreName = getScoreName();
            if (StringUtil.isEmpty(getScoreName())) {
                int beginIndex = getInputFile().lastIndexOf("/");
                if (beginIndex == -1) beginIndex = getInputFile().lastIndexOf("\\");
                int endIndex = getInputFile().lastIndexOf(".");
                if (endIndex == -1) endIndex = getInputFile().length();

                scoreName = getInputFile().substring(beginIndex + 1, endIndex);
            }

            if (dbTransaction.find(Score.class, "scoreName", scoreName) != null) throw new MusicXmlException("Score name " + scoreName + " already exists");

            ScoreHandler scoreHandler = handleXmlScoreFile(inputFile);
            Score score = scoreHandler.getScore();
            score.setScoreName(scoreName);

            System.err.println("Creating database record...");

            ItemOrderer.orderScoreItems(score);
            dbTransaction.create(score);

            // MusicData created separately
            for (Part part : score.getParts()) {
                for (Measure measure : part.getMeasures()) {
                    for (MusicData musicData : measure.getMusicDataList()) {
                        musicData.setMeasure(measure);
                        dbTransaction.create(musicData);
                    }
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        } catch (XmlException e) {
            throw new MusicXmlException(e.getMessage());
        } finally {
            try {
                DBSessionFactory.getInstance().closeTransaction();
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MusicXml2Db musicXmlDb = new MusicXml2Db();
            musicXmlDb.setOutputFile("NONE");
            musicXmlDb.setArgs(args);
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
