package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.builder.ScoreBuilder;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Score;
import org.curtis.properties.AppProperties;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

public class MusicXmlUtil {
    private MusicXmlUtil() {

    }

    public static ScoreHandler handleXmlScoreFile(String xmlFilename) throws XmlException {
        Document xmlDocument = XmlUtil.fileToDocument(xmlFilename);
        SchemaValidator.getInstance().validate(xmlDocument);

        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.handle(xmlDocument.getDocumentElement());

        return scoreHandler;
    }

    public static DBTransaction getDbTransaction() throws DBException {
        AppProperties.addPropertiesFile("properties/database");
        DBSessionFactory sessionFactory = DBSessionFactory.getInstance();

        return sessionFactory.getTransaction();
    }

    public static String getXmlResults(Score score) throws XmlException {
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        String results = scoreBuilder.build().toString();

        //SchemaValidator.getInstance().validate(results);

        return results;
    }
}
