package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.model.CartItem;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.Order;
import iti.jets.persistence.model.OrderItem;
import iti.jets.business.service.CartService;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@WebServlet(value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        boolean isOrder =  false;
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        EntityManager entityManager = connectionInstance.getEntityManager();
        Customer customer = (Customer) session.getAttribute("user");


        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        boolean isCartUpdated = CartService.chekCart(cart,entityManager);
        if (!isCartUpdated) {

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
                    isOrder = true;
                    String jsonResponse = String.format("{\"isOrder\": %b, \"orderId\": %d}", isOrder, order.getId());
                    resp.getWriter().write(jsonResponse);
            }
            catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    isOrder = false;
                    String jsonResponse = String.format("{\"isOrder\": %b, \"orderId\": false}", isOrder);
                    resp.getWriter().write(jsonResponse);
            }


        }
        else {
            String jsonResponse = String.format("{\"isOrder\": %b, \"orderId\": false}", isOrder);
            resp.getWriter().write(jsonResponse);
        }
    }
}
