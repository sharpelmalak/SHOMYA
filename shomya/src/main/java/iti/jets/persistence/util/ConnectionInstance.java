package iti.jets.persistence.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ConnectionInstance {
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public ConnectionInstance(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    // Lazy initialization: EntityManager will be created only when needed
    public EntityManager getEntityManager() {
        System.out.println("Connection Requested");
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
            System.out.println("Connection opened");
        }
        return entityManager;
    }


    // Close the EntityManager safely
    public void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
            System.out.println("Connection Closed");
        }
    }
}
