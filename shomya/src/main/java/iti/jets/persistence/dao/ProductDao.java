package iti.jets.persistence.dao;

import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    // New search method with parameters
    public List<Product> search(String name, String[] categoryIds, Float minPrice, Float maxPrice) {
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

        if (categoryIds != null && categoryIds.length > 0) {
            for (String categoryId : categoryIds) {
                predicates.add(cb.equal(product.get("category").get("id"), Integer.parseInt(categoryId)));
            }
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


    @Override
    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p WHERE p.deleted = false and p.category.deleted = false";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }
    @Override
    public Product findById(int id) {
        String jpql = "SELECT p FROM Product p WHERE p.deleted = false and p.id = :id and p.category.deleted = false";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void deleteById(int id)
    {
        try {
            entityManager.getTransaction().begin();
            String jpql = "UPDATE Product p SET p.deleted = true WHERE p.id = :id";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }

    public List<Product> findByName(String name) {
        // JPQL query to find products by name where isDeleted = 0
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :name AND p.deleted = false";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<Product> findByCategory(Category category) {
        // JPQL query to find products by name where isDeleted = 0
        String jpql = "SELECT p FROM Product p WHERE p.category = :category";
        return entityManager.createQuery(jpql, Product.class)
                .setParameter("category",category)
                .getResultList();
    }

    public List<Product> getPaginatedProducts(int pageNumber, int pageSize) {
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);

        // Set pagination parameters
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public long getTotalProductsCount() {
        String jpql = "SELECT COUNT(p) FROM Product p";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }

}
