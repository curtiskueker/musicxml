package org.curtis.database;

import org.curtis.properties.AppProperties;
import org.curtis.util.FileUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBSessionFactory {
    private static DBSessionFactory sessionFactory;

    // The actual database name
    private String databaseName;

    private String persistenceUnitName;

    private EntityManagerFactory emf;

    private EntityManager entityManager = null;

    // Holds the JpaTransaction for the thread
    private DBTransaction transaction = null;

    private static final Map<Object, Object> createDbProperties;
    private static final Map<Object, Object> generateSchemaProperties;
    static {
        createDbProperties = new HashMap<>();
        createDbProperties.put("hibernate.hbm2ddl.auto", "update");

        generateSchemaProperties = new HashMap<>();
        generateSchemaProperties.put("javax.persistence.schema-generation.scripts.action", "create");
        generateSchemaProperties.put("hibernate.hbm2ddl.delimiter", ";");
    }

    private Properties additionalProperties = new Properties();

    private DBSessionFactory() throws DBException {
        databaseName = AppProperties.getRequiredProperty("database.name");
        persistenceUnitName = AppProperties.getRequiredProperty("database.persistenceunit.name");
    }

    private void instantiateSessionFactory() throws DBException {
        try {
            String name = AppProperties.getString("database.username");
            String password = AppProperties.getString("database.password");
            String dbServer = AppProperties.getString("database.server");

            String url = "jdbc:mysql://" + dbServer + "/" + databaseName;

            Properties jpaProperties = new Properties();
            jpaProperties.put("hibernate.connection.url", url);
            jpaProperties.put("hibernate.connection.username", name);
            jpaProperties.put("hibernate.connection.password", password);

            jpaProperties.put("hibernate.connection.driver_class", AppProperties.getString("database.hibernate.driver_class"));
            jpaProperties.put("hibernate.dialect", AppProperties.getString("database.hibernate.dialect"));
            jpaProperties.put("hibernate.show_sql", AppProperties.getBoolean("database.hibernate.show_sql"));
            jpaProperties.put("hibernate.format_sql", AppProperties.getBoolean("database.hibernate.format_sql"));

            jpaProperties.putAll(additionalProperties);

            emf = Persistence.createEntityManagerFactory(persistenceUnitName, jpaProperties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e);
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
            e.printStackTrace();
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
            dbSessionFactory.getAdditionalProperties().putAll(createDbProperties);
            dbSessionFactory.instantiateSessionFactory();
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
        if(entityManager != null) {
            entityManager.close();
            entityManager = null;
        }

        transaction = null;
    }

    private DBTransaction createTransaction() throws DBException {
        DBTransaction dbTransaction = new DBTransaction(getEntityManager());
        dbTransaction.begin();

        return dbTransaction;
    }

    private EntityManager getEntityManager() throws DBException {
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
