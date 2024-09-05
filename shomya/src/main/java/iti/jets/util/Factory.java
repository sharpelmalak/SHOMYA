package iti.jets.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import java.util.logging.Level;

public class Factory {

    public static final EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("ecommerce");

    public static EntityManagerFactory getEntityMangerFactory(){
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            try {

                entityManagerFactory.close();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
    }
}
