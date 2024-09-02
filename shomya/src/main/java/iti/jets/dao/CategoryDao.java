package iti.jets.dao;

import iti.jets.model.Category;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryDao extends DAO<Category,Integer>{
    public CategoryDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Category> search(Category criteria) {
        return List.of();
    }
}
