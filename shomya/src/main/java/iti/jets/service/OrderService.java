package iti.jets.service;
import iti.jets.dao.OrderDAO;
import iti.jets.model.Order;
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

    // Setter method for OrderDAO
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
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
