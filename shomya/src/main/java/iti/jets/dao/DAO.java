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
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public T findById(int id) {
        T entity = null;
        try {
            entity = entityManager.find(persistentClass, id);
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error finding entity with id: " + id, e);
        }
        return entity;
    }

    public List<T> findAll() {
        List<T> entities = null;
        try {
            entities = entityManager.createQuery("from " + persistentClass.getName(), persistentClass).getResultList();
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error finding all entities", e);
        }
        return entities;
    }

    public boolean save(T entity) {
        boolean result;
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
        return result;
    }

    public T update(T entity) {
        T updatedEntity = null;
        try {
            entityManager.getTransaction().begin();
            updatedEntity = entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error updating entity", e);
        }
        return updatedEntity;
    }

    public void delete(T entity) {
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
    }

    public void deleteById(ID id) {
        T entity = findById((Integer) id);
        if (entity != null) {
            delete(entity);
        }
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
