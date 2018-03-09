package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.properties.AppProperties;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlException;

public class MusicXmlDb {
    public static String GENERATE_SCHEMA_FILE = null;
    public static String INPUT_FILE =  null;
    public static boolean CREATE_DB_SCHEMA = false;

    private void execute() throws MusicXmlException {
        try {
            AppProperties.addPropertiesFile("properties/database");
            DBSessionFactory sessionFactory = DBSessionFactory.getInstance();
            DBTransaction dbTransaction = sessionFactory.getTransaction();

            if (StringUtil.isNotEmpty(INPUT_FILE)) {
                ScoreHandler scoreHandler = MusicXmlUtil.handleXmlScoreFile(INPUT_FILE);
                dbTransaction.create(scoreHandler.getScore());
            }

            sessionFactory.closeTransaction();
        } catch (DBException | XmlException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        } finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("INPUT_FILE=")) {
                INPUT_FILE = arg.replace("INPUT_FILE=", "");
            } else if (arg.startsWith("SCHEMA_FILE=")) {
                GENERATE_SCHEMA_FILE = arg.replace("SCHEMA_FILE=", "");
            } else if (arg.equals("CREATE_SCHEMA")) {
                CREATE_DB_SCHEMA = true;
            }
        }
        MusicXmlDb musicXmlDb = new MusicXmlDb();
        try {
            musicXmlDb.execute();
        } catch (MusicXmlException e) {
            System.err.println("Fatal exception: " + e.getMessage());
        }
    }
}
