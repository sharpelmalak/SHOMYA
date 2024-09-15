package iti.jets.persistence.dao;

import iti.jets.persistence.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CategoryDao extends DAO<Category,Integer>{
    public CategoryDao(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public List<Category> search(Category criteria) {
        return List.of();
    }
    public List<Category> searchByname(String name)
    {
        // Create the JPQL query to search by name
        String jpql = "SELECT c FROM Category c WHERE c.name LIKE :name";

        // Create TypedQuery
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);

        // Set the name parameter with wildcard for partial matching
        query.setParameter("name", "%" + name + "%");

        // Execute the query and return the result
        return query.getResultList();
    }

    @Override
    public List<Category> findAll() {
        String jpql = "SELECT c FROM Category c WHERE c.deleted = false";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        return query.getResultList();
    }




    public void deleteById(int id)
    {
        try {
            entityManager.getTransaction().begin();
            String jpql = "UPDATE Category c SET c.deleted = true WHERE c.id = :id";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }
}
