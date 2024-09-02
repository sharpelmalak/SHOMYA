package iti.jets.dao;

import iti.jets.model.Admin;
import iti.jets.model.Category;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AdminDao extends DAO<Admin,Integer> {

    public AdminDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Admin> search(Admin criteria) {
        return List.of();
    }
    public boolean addCategory(Category category){
        CategoryDao categoryDao = new CategoryDao(entityManager);
        return categoryDao.save(category);
    }
    public Category updateCategory(Category category){
        CategoryDao categoryDao = new CategoryDao(entityManager);
        return categoryDao.update(category);
    }
    public void deleteCategory(Category category){
        CategoryDao categoryDao = new CategoryDao(entityManager);
        categoryDao.delete(category);
    }
    public void deleteCategoryById(int id){
        CategoryDao categoryDao = new CategoryDao(entityManager);
        categoryDao.deleteById(id);
    }



}
