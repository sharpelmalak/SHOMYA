package iti.jets.service;


import iti.jets.dao.CategoryDAO;
import iti.jets.model.Category;
import iti.jets.model.Product;
import iti.jets.model.User;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }
    public List<Category> getAllCategories() {
        return  categoryDAO.findAll();
    }
    public Category getCategoryById(int id) {
        return categoryDAO.findById(id);
    }
    public void addCategory(Category category) {
        categoryDAO.save(category);
    }
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }
    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }
    public void deleteCategoryById(int id) {
        categoryDAO.deleteById(id);
    }
    public List<Product> getProductsOfCategoryById(int id) {
        Category category = categoryDAO.findById(id);
        return new ArrayList<Product>(category.getProducts());
    }
    public List<User> getInterestedUsersOfCategoryById(int id) {
        Category category = categoryDAO.findById(id);
        return new ArrayList<User>(category.getUsers());
    }


}
