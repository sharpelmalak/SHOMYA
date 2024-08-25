package iti.jets.service;

import iti.jets.dao.CategoryDAO;
import iti.jets.dao.ProductDAO;
import iti.jets.model.Product;

import java.util.List;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }
    public List<Product> getProducts() {
        return productDAO.findAll();
    }
    public Product getProduct(int id) {
        return productDAO.findById(id);
    }
    public void addProduct(Product product) {
        productDAO.save(product);
    }
    public void updateProduct(Product product) {
        productDAO.save(product);
    }
    public void deleteProduct(Product product)
    {
        productDAO.delete(product);
    }
    public void deleteProductById(int id) {
        productDAO.deleteById(id);
    }
    public int getProductCountById(int id) {
        Product product = productDAO.findById(id);
        return product.getQuantity();
    }

}
