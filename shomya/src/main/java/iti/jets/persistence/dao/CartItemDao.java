package iti.jets.persistence.dao;

import iti.jets.persistence.model.CartItem;


import iti.jets.persistence.model.CartItemId;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CartItemDao extends DAO<CartItem, CartItemId> {


    public CartItemDao(EntityManager entityManager)
    {
        super(entityManager);
    }


    // ************************SEARCH*********************************** //
    @Override
    public List<CartItem> search(CartItem criteria) {
        return null;
    }


    public List<CartItem> findAll(int customerId) {
        // Query to get all CartItems for the customer
        return entityManager.createQuery(
                        "FROM CartItem c WHERE c.customer.id = :customerId", CartItem.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public void deleteAll(int customerId) {
        // Start a transaction manually
        entityManager.getTransaction().begin();

        try {
            // JPQL query to delete all CartItems for the customer
            int deletedCount = entityManager.createQuery(
                            "DELETE FROM CartItem c WHERE c.customer.id = :customerId")
                    .setParameter("customerId", customerId)
                    .executeUpdate();

            // Commit the transaction
            entityManager.getTransaction().commit();
            System.out.println("Deleted " + deletedCount + " cart items for customer ID: " + customerId);
        } catch (Exception e) {
            // Rollback transaction in case of error
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting cart items for customer ID: " + customerId, e);
        }
    }
}

