package iti.jets.persistence.dao;

import iti.jets.persistence.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    // Method to search products by name and price range
    public List<Product> searchByNameAndPrice(String name, float minPrice, float maxPrice) {
        // Initialize CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        // Define the Root object for the query (Product entity)
        Root<Product> product = query.from(Product.class);
        // Create a list to hold the predicates (AND conditions)
        List<Predicate> predicates = new ArrayList<>();
        // Add predicate for name (if name is provided)
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + name.toLowerCase() + "%"));
        }
        // Add predicate for price range (since minPrice and maxPrice are mandatory)
        predicates.add(cb.between(product.get("price"), minPrice, maxPrice));
        // Combine all predicates with AND
        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
        // Set the WHERE clause in the query
        query.select(product).where(finalPredicate);
        // Execute the query and return the results
        return entityManager.createQuery(query).getResultList();
    }
    // Method to search products by name and price range
    public List<Product> searchByName(String name) {
        List<Product> filteredProducts;
        // Define JPQL query to search for products by name (case-insensitive)
        String jpql = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE :name";
        // Create the query and set the parameter
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", "%" + name.toLowerCase() + "%");
        // Execute the query and return the result list
        filteredProducts=query.getResultList();
        return filteredProducts;
    }
}

