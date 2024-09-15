package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CheckoutService;
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
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        Customer customer = (Customer) session.getAttribute("user");


        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        boolean isCartUpdated = CartService.checkCart(cart,connectionInstance);
        if (!isCartUpdated) {
           Order order = CheckoutService.checkout(connectionInstance,customer,cart);
           if(order!=null)
           {
               String jsonResponse = String.format("{\"isOrder\": true, \"orderId\": %d}", order.getId());
               resp.getWriter().write(jsonResponse);
               return;
           }
        }
        String jsonResponse = String.format("{\"isOrder\": %b, \"orderId\": false}", false);
        resp.getWriter().write(jsonResponse);

    }
}
