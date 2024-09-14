package iti.jets.persistence.dao;

import iti.jets.persistence.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ProductDao extends DAO<Product,Integer> {

    public ProductDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Product> search(Product criteria) {
        return List.of();
    }

    // New search method with parameters
    public List<Product> search(String name, Integer categoryId, Double minPrice, Double maxPrice) {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        // Create a list to hold predicates
        List<Predicate> predicates = new ArrayList<>();

        // Add predicates based on parameters
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (categoryId != null) {
            predicates.add(cb.equal(product.get("category").get("id"), categoryId));
        }

        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        }

        // Combine all predicates with AND
        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Execute query
        return entityManager.createQuery(query).getResultList();
    }
}
