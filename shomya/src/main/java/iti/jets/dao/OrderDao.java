package iti.jets.dao;

import iti.jets.model.Customer;
import iti.jets.model.Order;
import iti.jets.model.User;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderDao extends DAO<Order,Integer>{
    public List<Order> searchByUserId(int userId) {
        List<Order> orders=new ArrayList<>();
        UserDao userDao= new UserDao();
        Customer user = (Customer) userDao.findById(1);
        if (user != null) {
            System.out.println(user.getJob());
            Set<Order> orderSet= user.getOrders();
            //List<Order> orders = (List<Order>) user.getOrders();
            for(Order order:orderSet) {
                orders.add(order);
            }
            return orders;
        }
        else{
            return null;
        }
    }

    public List<Order> getAllOrdersWithinTimestampRange(Timestamp startTime, Timestamp endTime) {
        getConnection();
        List<Order> orders ;
        try {
            TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end", Order.class);
            query.setParameter("start", startTime);
            query.setParameter("end", endTime);
            orders = query.getResultList();
        }catch (PersistenceException e ){
            orders=null;
        }
        closeConnection();
        return orders;
    }
        @Override
    public List<Order> search(Order criteria) {
        return List.of();
    }

    private OrderDao orderDAO;

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }
    public Order getOrderById(int id) {
        return orderDAO.findById(id);
    }
    public void addOrder(Order order) {
        orderDAO.save(order);
    }
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }
    public void deleteOrder(Order order) {
        orderDAO.delete(order);
    }
    public void deleteOrderById(int id) {
        orderDAO.deleteById(id);
    }
}

