package iti.jets.business.service;

import iti.jets.persistence.model.CartItem;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.Order;
import iti.jets.persistence.model.OrderItem;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckoutService {
    public static Order checkout(ConnectionInstance connectionInstance, Customer customer, List<CartItem> cart) {
        EntityManager entityManager = connectionInstance.getEntityManager();
        float total = CartService.calculateTotalCart(cart)+10.0F;
        Order order = new Order(customer,total);
        Set<OrderItem> orderItems = new HashSet<>();
        try{

            entityManager.getTransaction().begin();
            entityManager.persist(order);
            System.out.println("Order created with id: " + order.getId());
            customer.setCreditLimit(customer.getCreditLimit()-total);
            entityManager.merge(customer);
            for(CartItem item : cart) {
                item.getProduct().setQuantity(item.getProduct().getQuantity()-item.getQuantity());
                entityManager.merge(item.getProduct());
                OrderItem orderItem = new OrderItem(order,item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
                entityManager.persist(orderItem);
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);
            entityManager.merge(order);
            cart.clear();
            entityManager.getTransaction().commit();
            return order;

        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }
}
