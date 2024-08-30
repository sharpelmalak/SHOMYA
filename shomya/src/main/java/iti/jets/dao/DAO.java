package iti.jets.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO<T, ID> {

    private static final Logger LOGGER = Logger.getLogger(DAO.class.getName());
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce");

    protected EntityManager entityManager;

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public DAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    protected boolean getConnection()
    {
        boolean result;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            result = true;
        }
        catch (PersistenceException e)
        {
            result = false;
        }
        return result;
    }
    protected boolean closeConnection()
    {
        boolean result;
        try{
            entityManager.close();
            result = true;
        }
        catch (PersistenceException e)
        {
            result = false;
        }
        return result;
    }
    public T findById(int id) {
        T entity = null;
        getConnection();
        try {

            entity = entityManager.find(persistentClass, id);

        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error finding entity with id: " + id, e);
        }
        closeConnection();
        return entity;
    }

    public List<T> findAll() {
        List<T> entities = null;
        getConnection();
        try {
            entities = entityManager.createQuery("from " + persistentClass.getName(), persistentClass).getResultList();
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error finding all entities", e);
        }
        closeConnection();
        return entities;
    }

    public boolean save(T entity) {
        boolean result;
        getConnection();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            result = true;
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error saving entity", e);
            result = false;
        }
        closeConnection();
        return result;
    }

    public T update(T entity) {
        T updatedEntity = null;
        getConnection();
        try {
            entityManager.getTransaction().begin();
            updatedEntity = entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error updating entity", e);
        }
        closeConnection();
        return updatedEntity;
    }

    public void delete(T entity) {
        getConnection();
        try {
            entityManager.getTransaction().begin();
            if (entityManager.contains(entity)) {
                entityManager.remove(entity);
            } else {
                T mergedEntity = entityManager.merge(entity);
                entityManager.remove(mergedEntity);
            }
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error deleting entity", e);
        }
        closeConnection();
    }

    public void deleteById(ID id) {
        T entity = findById((Integer) id);
        getConnection();
        if (entity != null) {
            delete(entity);
        }
        closeConnection();
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            try {

                entityManagerFactory.close();
            } catch (PersistenceException e) {
                LOGGER.log(Level.SEVERE, "Error closing EntityManagerFactory", e);
            }
        }
    }
    public abstract List<T> search(T criteria);
}
