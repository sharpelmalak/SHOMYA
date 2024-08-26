package iti.jets.dao;

import iti.jets.model.Order;
import iti.jets.model.Product;

import java.util.List;

public class OrderDAO extends DAO<Order,Integer> {
    public OrderDAO() { super(); }

    @Override
    public List<Order> search(Order order) {
        return List.of();
    }

    // Method to fetch order history by customer ID
//    public static Order findByCustomerId(int id) {
//        Order order = new Order();
//        return order;
//    }
}
