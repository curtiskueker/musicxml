package org.curtis.database;

import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.AppProperties;
import org.curtis.util.StringUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class DBSessionFactory {
    private static DBSessionFactory sessionFactory;

    // The actual database name
    private String databaseName;

    private String persistenceUnitName;

    private EntityManagerFactory emf;

    private static final ThreadLocal<EntityManager> entityManager = new ThreadLocal<>();

    // Holds the JpaTransaction for the thread
    private static final ThreadLocal<DBTransaction> transaction = new ThreadLocal<>();

    private DBSessionFactory() throws DBException {
        databaseName = AppProperties.getRequiredProperty("database.name");
        persistenceUnitName = AppProperties.getRequiredProperty("database.persistenceunit.name");

        instantiateSessionFactory();
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

            if (StringUtil.isNotEmpty(MusicXmlUtil.GENERATE_SCHEMA_FILE)) {
                jpaProperties.put("javax.persistence.schema-generation.scripts.create-target", MusicXmlUtil.GENERATE_SCHEMA_FILE);
                jpaProperties.put("javax.persistence.schema-generation.scripts.action", "create");
                jpaProperties.put("hibernate.hbm2ddl.delimiter", ";");
            } else if (MusicXmlUtil.CREATE_DB_SCHEMA){
                jpaProperties.put("hibernate.hbm2ddl.auto", "update");
            }

            emf = Persistence.createEntityManagerFactory(persistenceUnitName, jpaProperties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public static synchronized DBSessionFactory getInstance() throws DBException {
        if (sessionFactory == null) {
            setupSessionFactory();
        }

        return sessionFactory;
    }

    private static void setupSessionFactory() throws DBException {
        try {
            sessionFactory = new DBSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    public synchronized DBTransaction getTransaction() throws DBException {
        DBTransaction dbTransaction = transaction.get();

        if (dbTransaction == null) {
            dbTransaction = createTransaction();
            transaction.set(dbTransaction);
        }
        return dbTransaction;
    }

    public synchronized void closeTransaction() throws DBException {
        EntityManager em = entityManager.get();
        if(em != null) {
            em.close();
            entityManager.set(null);
        }

        DBTransaction dbTransaction = transaction.get();
        if(dbTransaction != null) {
            transaction.set(null);
        }
    }

    private DBTransaction createTransaction() throws DBException {
        DBTransaction dbTransaction = new DBTransaction(getEntityManager());
        dbTransaction.begin();
        return dbTransaction;
    }

    public EntityManager getEntityManager() throws DBException {
        EntityManager em = entityManager.get();
        if (em == null) {
            em = createEntityManager();
            entityManager.set(em);
        }
        return em;
    }

    private EntityManager createEntityManager() throws DBException {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
