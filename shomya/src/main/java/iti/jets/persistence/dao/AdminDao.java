package iti.jets.persistence.dao;

import iti.jets.persistence.model.Admin;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.Product;
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



    public List<Category> getallCategories() {
        CategoryDao categoryDao = new CategoryDao(entityManager);
        return categoryDao.findAll();
    }
    public Category getCategory(int id){
        CategoryDao categoryDao = new CategoryDao(entityManager);
        return categoryDao.findById(id);
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




    public List<Product> getallProducts() {
        ProductDao productDao = new ProductDao(entityManager);
        return productDao.findAll();
    }
    public Product getProduct(int id){
        ProductDao productDao = new ProductDao(entityManager);
        return productDao.findById(id);
    }
    public boolean addProduct(Product product){
        ProductDao productDao = new ProductDao(entityManager);
        return productDao.save(product);
    }

    public Product updateProduct(Product product){
        ProductDao productDao = new ProductDao(entityManager);
        return productDao.update(product);
    }
    public void deleteProduct(Product product){
        ProductDao productDao = new ProductDao(entityManager);
        productDao.delete(product);
    }
    public void deleteProductById(int id){
        ProductDao productDao = new ProductDao(entityManager);
        productDao.deleteById(id);
    }

    public List<Customer> getAllCustomers()
    {
        CustomerDao customerDao = new CustomerDao(entityManager);
        return customerDao.findAll();
    }
    public Customer getCustomer(int id){
        CustomerDao customerDao = new CustomerDao(entityManager);
        return customerDao.findById(id);
    }


}
