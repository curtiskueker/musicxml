package org.curtis.database;

import org.curtis.properties.AppProperties;
import org.curtis.ui.task.TaskConstants;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.hibernate.tool.schema.spi.SchemaManagementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBSessionFactory {
    private static DBSessionFactory sessionFactory;
    private static boolean schemaValidated = false;

    private String databaseName;
    private String databaseType;

    private String persistenceUnitName;

    private EntityManagerFactory emf;

    private EntityManager entityManager = null;

    private DBTransaction transaction = null;

    private static final Map<Object, Object> createDbProperties;
    private static final Map<Object, Object> validateDbProperties;
    private static final Map<Object, Object> generateSchemaProperties;

    private static final Map<Object, Object> mySqlProperties;
    private static final Map<Object, Object> postgresProperties;
    private static final Map<Object, Object> oracleProperties;

    static {
        mySqlProperties = new HashMap<>();
        mySqlProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        mySqlProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        postgresProperties = new HashMap<>();
        postgresProperties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        postgresProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        oracleProperties = new HashMap<>();
        oracleProperties.put("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver");
        oracleProperties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");

        createDbProperties = new HashMap<>();
        createDbProperties.put("hibernate.hbm2ddl.auto", "update");

        validateDbProperties = new HashMap<>();
        validateDbProperties.put("hibernate.hbm2ddl.auto", "validate");

        generateSchemaProperties = new HashMap<>();
        generateSchemaProperties.put("javax.persistence.schema-generation.scripts.action", "create");
        generateSchemaProperties.put("hibernate.hbm2ddl.delimiter", ";");
    }

    private Properties databaseProperties = new Properties();
    private Properties additionalProperties = new Properties();

    private DBSessionFactory() {
        databaseName = AppProperties.getRequiredProperty("database.name");
        databaseType = AppProperties.getRequiredProperty("database.type");
        persistenceUnitName = AppProperties.getRequiredProperty("database.persistenceunit.name");
    }

    private void instantiateSessionFactory() throws DBException {
        instantiateSessionFactory(false);
    }

    private void instantiateSessionFactory(boolean createTables) throws DBException {
        try {
            if (StringUtil.isEmpty(databaseType)) throw new DBException("Error: Database type undefined");

            System.err.println("Initializing database connection...");
            String name = AppProperties.getString("database.username");
            String password = AppProperties.getString("database.password");
            String dbServer = AppProperties.getString("database.server");

            Properties jpaProperties = new Properties();

            switch (databaseType) {
                case DBConstants.DATABASE_MYSQL:
                    databaseProperties.putAll(mySqlProperties);
                    break;
                case DBConstants.DATABASE_POSTGRES:
                    databaseProperties.putAll(postgresProperties);
                    break;
                case DBConstants.DATABASE_ORACLE:
                    databaseProperties.putAll(oracleProperties);
                    break;
                default:
                    throw new DBException("Error: Unknown database type: " + databaseType);
            }
            jpaProperties.putAll(databaseProperties);

            String protocolConnector = databaseType.equals(DBConstants.DATABASE_ORACLE) ? ":thin:@" : "://";
            String url = "jdbc:" + databaseType + protocolConnector + dbServer + "/" + databaseName;

            jpaProperties.put("hibernate.connection.url", url);
            jpaProperties.put("hibernate.connection.username", name);
            jpaProperties.put("hibernate.connection.password", password);

            jpaProperties.put("hibernate.show_sql", AppProperties.getBoolean("database.hibernate.show_sql"));
            jpaProperties.put("hibernate.format_sql", AppProperties.getBoolean("database.hibernate.format_sql"));

            if (createTables) jpaProperties.putAll(createDbProperties);
            else if (!schemaValidated) jpaProperties.putAll(validateDbProperties);

            jpaProperties.putAll(additionalProperties);

            emf = Persistence.createEntityManagerFactory(persistenceUnitName, jpaProperties);
            schemaValidated = true;

            System.err.println("Database initialized.");
        } catch (Exception e) {
            if (e.getCause() instanceof SchemaManagementException) {
                createDb();
                instantiateSessionFactory();
                if (!schemaValidated) throw new DBException("Error: Unable to create database tables");
            }
            else throw new DBException(e);
        }
    }

    public static synchronized DBSessionFactory getInstance() throws DBException {
        if (sessionFactory == null) setupSessionFactory();

        return sessionFactory;
    }

    private static void setupSessionFactory() throws DBException {
        try {
            DBSessionFactory dbSessionFactory = new DBSessionFactory();
            dbSessionFactory.instantiateSessionFactory();
            sessionFactory = dbSessionFactory;
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    private Properties getAdditionalProperties() {
        return additionalProperties;
    }

    public static void clearSessionFactory() throws DBException {
        if (sessionFactory == null) return;

        sessionFactory.closeTransaction();
        sessionFactory = null;
    }

    public static void createDb() throws DBException {
        try {
            DBSessionFactory dbSessionFactory = new DBSessionFactory();
            dbSessionFactory.instantiateSessionFactory(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public static void generateDbSchema(String fileLocation) throws DBException {
        try {
            DBSessionFactory dbSessionFactory = new DBSessionFactory();
            String tempSchemaFile = "/tmp/" + FileUtil.getTempFilename("sql");
            dbSessionFactory.getAdditionalProperties().put("javax.persistence.schema-generation.scripts.create-target", tempSchemaFile);
            dbSessionFactory.getAdditionalProperties().putAll(generateSchemaProperties);
            dbSessionFactory.instantiateSessionFactory();
            FileUtil.moveFile(tempSchemaFile, fileLocation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public synchronized DBTransaction getTransaction() throws DBException {
        if (transaction == null) {
            transaction = createTransaction();
        }

        return transaction;
    }

    public synchronized void closeTransaction() throws DBException {
        if (transaction == null) return;

        if (transaction.isActive()) transaction.commit();
        transaction = null;

        if(entityManager != null) {
            entityManager.close();
            entityManager = null;
        }
    }

    private DBTransaction createTransaction() throws DBException {
        DBTransaction dbTransaction = new DBTransaction(getEntityManager());
        dbTransaction.begin();

        return dbTransaction;
    }

    public EntityManager getEntityManager() throws DBException {
        if (entityManager == null) {
            entityManager = createEntityManager();
        }

        return entityManager;
    }

    private EntityManager createEntityManager() throws DBException {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
