package iti.jets.business.service;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.util.ConnectionInstance;

import java.util.List;

public class CategoryService {


    public static List<Category> getCategories(ConnectionInstance connection) {
        CategoryDao categoryDao = new CategoryDao(connection.getEntityManager());
        // call all is deleted false
        List<Category> categoryList = categoryDao.findAll();
        return categoryList;
    }


    public static Category getCategory(ConnectionInstance connection, int id) {
        CategoryDao categoryDao = new CategoryDao(connection.getEntityManager());
        return categoryDao.findById(id);
    }
    public static boolean addCategory(String name, byte[]image, ConnectionInstance connection) {
        try{
            Category category = new Category(name,image);
            CategoryDao categoryDao = new CategoryDao(connection.getEntityManager());
            // Save the file to the specified directory
            categoryDao.save(category);
            return true;
        }
        catch(Exception e){
            return false;
        }


    }

    public static void deleteCategoryById(int id, ConnectionInstance connection) {
        CategoryDao categoryDao = new CategoryDao(connection.getEntityManager());
        categoryDao.deleteById(id);
    }
}
