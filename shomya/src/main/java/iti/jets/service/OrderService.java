package iti.jets.service;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import iti.jets.dao.CategoryDAO;
import iti.jets.dao.OrderDAO;
import iti.jets.dao.UserDAO;
import iti.jets.model.Category;
import iti.jets.model.Order;
import iti.jets.model.Product;
import iti.jets.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }
    public Order getOrderById(int id) {
        return orderDAO.findById(id);
    }
    public void addOrder(Order order) {
        orderDAO.save(order);
    }
    public void updateCategory(Order order) {
        orderDAO.update(order);
    }
    public void deleteOrder(Order order) {
        orderDAO.delete(order);
    }
    public void deleteOrderById(int id) {
        orderDAO.deleteById(id);
    }


//      // Method to retrieve order history based on customer ID
//        public List<Order> reviewCustomerOrderHistoryByID(int id) {
//            Order order = OrderDAO.findByCustomerId(id);
//            if (order != null) {
//                return new ArrayList<>(order.getUsers());
//            } else {
//                return Collections.emptyList();
//            }
//        }
}
