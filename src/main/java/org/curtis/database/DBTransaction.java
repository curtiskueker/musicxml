package org.curtis.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DBTransaction {
    private EntityManager em;
    private EntityTransaction transaction;

    public DBTransaction(EntityManager em) {
        this.em = em;
        this.transaction = em.getTransaction();
    }

    public void begin() throws DBException {
        checkDatabase();
    }

    public void commit() throws DBException {
        checkDatabase();

        try {
            transaction.commit();
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean isActive() {
        return (transaction != null && transaction.isActive());
    }

    public void create(Object object) throws DBException {
        checkDatabase();

        try {
            em.persist(object);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public <T> T getObjectById(Class<T> classObject, int id) throws DBException {
        checkDatabase();

        try {
            return em.find(classObject, id);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public <T extends DatabaseItem> T find(Class<T> classType, String field, Object value) throws DBException {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(field), value));

        return em.createQuery(criteriaQuery).getSingleResult();
    }

    // Private helper method that checks that the database is in a valid
    // state to execute against.  Throws an exception if it is not.
    private void checkDatabase() throws DBException {
        if (em == null || !em.isOpen()) {
            throw new DBException("Database is closed");
        }
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }
}
