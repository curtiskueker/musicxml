package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

import java.io.File;

public class Db2MusicXml {
    private static Integer SCORE_ID;
    private static String OUTPUT_FILE;
    private static String FILENAME;

    private void execute() throws MusicXmlException {
        try {
            Score score = null;
            if (SCORE_ID != null) {
                score = MusicXmlUtil.getDbTransaction().getObjectById(Score.class, SCORE_ID);
            } else if (StringUtil.isNotEmpty(FILENAME)) {
                File xmlFile = new File(FILENAME);
                score = MusicXmlUtil.getDbTransaction().find(Score.class, "filename", xmlFile.getName());
            }
            if (score == null) {
                throw new MusicXmlException("Score not found");
            }

            String results = MusicXmlUtil.getXmlResults(score);

            FileUtil.stringToFile(results, OUTPUT_FILE);
        } catch (DBException | FileException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        } catch (Throwable e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("SCORE_ID=")) {
                String scoreId = arg.replace("SCORE_ID=", "");
                if (StringUtil.isNotEmpty(scoreId)) SCORE_ID = Integer.parseInt(scoreId);
            } else if (arg.startsWith("OUTPUT_FILE=")) {
                OUTPUT_FILE = arg.replace("OUTPUT_FILE=", "");
            } else if (arg.equals("DEBUG")) {
                MusicXmlUtil.DEBUG = true;
            } else if (arg.startsWith("FILENAME=")) {
                FILENAME = arg.replace("FILENAME=", "");
            }
        }
        try {
            if (StringUtil.isEmpty(OUTPUT_FILE)) {
                throw new MusicXmlException("Output file not indicated");
            }

            Db2MusicXml db2MusicXml = new Db2MusicXml();
            db2MusicXml.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
