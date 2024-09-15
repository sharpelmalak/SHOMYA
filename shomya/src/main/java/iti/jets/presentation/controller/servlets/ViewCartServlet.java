package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.model.CartItem;
import iti.jets.persistence.model.Customer;
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
import java.util.List;

//@WebServlet("/viewcart")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("user");
        if (customer != null) {
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            EntityManager entityManager = connectionInstance.getEntityManager();
            CartService.checkCart((List<CartItem>) session.getAttribute("cart"),connectionInstance);
            request.getRequestDispatcher("/resources/jsp/cart.jsp").forward(request, response);
        }
        else response.sendRedirect("/shomya");
    }
}
