package iti.jets.dao;

import iti.jets.model.Customer;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CustomerDao extends DAO<Customer,Integer> {

    public CustomerDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Customer> search(Customer criteria) {
        return List.of();
    }
}
