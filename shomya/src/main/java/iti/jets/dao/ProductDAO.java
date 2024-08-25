package iti.jets.dao;

import iti.jets.model.Product;

import java.util.List;

public class ProductDAO extends DAO<Product,Integer> {


    public ProductDAO() {
        super();
    }
    @Override
    public List<Product> search(Product criteria) {
        return List.of();
    }
}
