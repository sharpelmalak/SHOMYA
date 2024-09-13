package iti.jets.dao;

import iti.jets.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ProductDao extends DAO<Product,Integer>{
    public ProductDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Product> search(Product criteria) {
        return List.of();
    }



    // Search by product name
    public List<Product> searchByName(String name) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    // Filter by category name
    public List<Product> filterByCategory(String categoryName) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.category.name = :categoryName");
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }

    // Filter by price range
    public List<Product> filterByPriceRange(float minPrice, float maxPrice) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice");
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

}
