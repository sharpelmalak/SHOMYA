package iti.jets.dao;

import iti.jets.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductDao extends DAO<Product,Integer>{
    public ProductDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Product> search(Product criteria) {
        return List.of();
    }
}
