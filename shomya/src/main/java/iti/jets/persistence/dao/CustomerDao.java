package iti.jets.persistence.dao;

import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerDao extends DAO<Customer,Integer> {

    public CustomerDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Customer> search(Customer criteria) {
        return List.of();
    }

    // Method to get orders based on customer ID
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        TypedQuery<Order> query = entityManager.createQuery(
                "SELECT o FROM Order o WHERE o.customer.id = :customerId", Order.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public List<Category> findInterests(int id) {
        try {

            String jpql = "SELECT c.categories FROM Customer c WHERE c.id = :id";
            TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
            query.setParameter("id",id);
            return query.getResultList();
        }
        catch (Exception e) {
            return null;
        }

    }


}

