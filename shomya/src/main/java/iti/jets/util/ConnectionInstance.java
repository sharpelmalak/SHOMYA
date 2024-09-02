package iti.jets.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ConnectionInstance {
    private EntityManager entityManager;

    public ConnectionInstance(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void closeEntityManager(){
        entityManager.close();
    }

}
