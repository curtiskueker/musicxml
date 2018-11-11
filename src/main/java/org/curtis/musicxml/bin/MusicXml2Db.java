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

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class MusicXml2Db {
    public static String GENERATE_SCHEMA_FILE = null;
    public static String INPUT_FILENAME =  null;
    public static boolean CREATE_DB_SCHEMA = false;

    private void execute() throws MusicXmlException {
        try {
            DBTransaction dbTransaction = MusicXmlUtil.getDbTransaction();

            if (StringUtil.isNotEmpty(INPUT_FILENAME)) {
                File inputFile = new File(INPUT_FILENAME);
                ScoreHandler scoreHandler = MusicXmlUtil.handleXmlScoreFile(inputFile);
                Score score = scoreHandler.getScore();
                score.setFilename(inputFile.getName());

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
            }

            DBSessionFactory.getInstance().closeTransaction();
        } catch (DBException | XmlException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("INPUT_FILE=")) {
                INPUT_FILENAME = arg.replace("INPUT_FILE=", "");
            } else if (arg.startsWith("SCHEMA_FILE=")) {
                GENERATE_SCHEMA_FILE = arg.replace("SCHEMA_FILE=", "");
            } else if (arg.equals("CREATE_SCHEMA")) {
                CREATE_DB_SCHEMA = true;
            } else if (arg.equals("DEBUG")) {
                DEBUG = true;
            }
        }
        MusicXml2Db musicXmlDb = new MusicXml2Db();
        try {
            musicXmlDb.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
