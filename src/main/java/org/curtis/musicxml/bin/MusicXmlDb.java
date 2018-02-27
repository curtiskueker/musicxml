package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.properties.AppProperties;

public class MusicXmlDb {
    public static String GENERATE_SCHEMA_FILE = null;
    public static boolean CREATE_DB_SCHEMA = false;

    private void execute() {
        try {
            AppProperties.addPropertiesFile("properties/database");
            DBSessionFactory sessionFactory = DBSessionFactory.getInstance();
            sessionFactory.getTransaction();
            sessionFactory.closeTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("SCHEMA_FILE=")) {
                GENERATE_SCHEMA_FILE = arg.replace("SCHEMA_FILE=", "");
            } else if (arg.equals("CREATE_SCHEMA")) {
                CREATE_DB_SCHEMA = true;
            }
        }
        MusicXmlDb musicXmlDb = new MusicXmlDb();
        musicXmlDb.execute();
    }
}
