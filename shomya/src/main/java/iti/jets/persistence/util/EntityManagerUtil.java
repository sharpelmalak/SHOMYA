package iti.jets.persistence.util;

import jakarta.persistence.EntityManager;

/** Provides access to the entity manager.  */
public class EntityManagerUtil
{
    public static final ThreadLocal<EntityManager>
            ENTITY_MANAGERS = new ThreadLocal<EntityManager>();

    /** Returns a fresh EntityManager */
    public static EntityManager getEntityManager()
    {
        return ENTITY_MANAGERS.get();
    }
}
