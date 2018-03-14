package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBTransaction;
import org.curtis.exception.FileException;
import org.curtis.musicxml.builder.ScoreBuilder;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

public class Db2MusicXml {
    public static Integer SCORE_ID;
    public static String OUTPUT_FILE;

    private void execute() throws MusicXmlException {
        try {
            Score score = null;
            if (SCORE_ID != null) {
                DBTransaction dbTransaction = MusicXmlUtil.getDbTransaction();
                score = dbTransaction.getObjectById(Score.class, SCORE_ID);
            }
            if (score == null) {
                throw new MusicXmlException("Score not found");
            }

            ScoreBuilder scoreBuilder = new ScoreBuilder(score);
            String results = scoreBuilder.build().toString();

            Document xmlDocument = XmlUtil.stringToDocument(results);
            SchemaValidator.getInstance().validate(xmlDocument);

            FileUtil.stringToFile(results, OUTPUT_FILE);
        } catch (DBException | FileException | XmlException e) {
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
            } else if (arg.startsWith("OUTPUT_FILE=")) {
                OUTPUT_FILE = arg.replace("OUTPUT_FILE=", "");
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
        }
    }
}