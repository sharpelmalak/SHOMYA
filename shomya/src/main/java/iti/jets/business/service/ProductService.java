package iti.jets.business.service;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Admin;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;

import java.util.List;

public class ProductService {

    public static List<Product> getProducts(ConnectionInstance connection) {
        ProductDao dao = new ProductDao(connection.getEntityManager());
        return dao.findAll();
    }
    public static void deleteProduct(ConnectionInstance connection, int id) {
        ProductDao dao = new ProductDao(connection.getEntityManager());
        dao.deleteById(id);
    }
    public static boolean addProduct(ConnectionInstance connectionInstance, Admin admin ,String name,
                                     int catId,float price,int quantity,String description,
                                     byte[] imageData) {
        try {
            CategoryDao categoryDao= new CategoryDao(connectionInstance.getEntityManager());
            Category category =categoryDao.findById(catId);
            ProductDao productDao=new ProductDao(connectionInstance.getEntityManager());
            Product product = new Product(admin,category,name, price, quantity, description, imageData);
            productDao.save(product);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static Product updateProduct(ConnectionInstance connectionInstance,int id,String name,
                                     int catId,float price,int quantity,String description,
                                     byte[] imageData) {
        try {
            CategoryDao categoryDao= new CategoryDao(connectionInstance.getEntityManager());
            Category category =categoryDao.findById(catId);
            ProductDao productDao=new ProductDao(connectionInstance.getEntityManager());
            Product product = productDao.findById(id);
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(description);
            if(imageData!=null)
            {
                product.setImage(imageData);
            }
            productDao.update(product);
            return product;
        }
        catch (Exception e) {
            return null;
        }
    }
    public static Product getProduct(ConnectionInstance connectionInstance, int id) {
        ProductDao dao = new ProductDao(connectionInstance.getEntityManager());
        return dao.findById(id);
    }

    public static List<Product> getProductByName(ConnectionInstance connectionInstance, String name) {
        ProductDao dao = new ProductDao(connectionInstance.getEntityManager());
        return dao.findByName(name);
    }


    public static List<Product> searchProducts(ConnectionInstance connectionInstance, String name,Float minprice,Float maxprice,String[] selectedCategories) {
        ProductDao dao = new ProductDao(connectionInstance.getEntityManager());
        return dao.search(name,selectedCategories,minprice,maxprice);

    }

}
