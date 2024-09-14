package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.model.CartItem;
import iti.jets.business.service.CartService;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

//@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            // to do handle cart item
            ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart != null && cart.size() > 0) {
                EntityManager entityManager = connectionInstance.getEntityManager();
                CartService.saveCart(cart, entityManager);
            }
            session.invalidate();
        }
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        sessionCookie.setMaxAge(0); // Invalidate the cookie
        sessionCookie.setPath("/"); // Make sure it applies to the entire app
        resp.addCookie(sessionCookie);
        resp.sendRedirect("/shomya");
    }
}
