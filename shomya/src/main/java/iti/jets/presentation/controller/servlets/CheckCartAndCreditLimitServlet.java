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

//@WebServlet(value = "/checkCartAndCredit")
public class CheckCartAndCreditLimitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        // Prepare JSON response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        boolean isCartChaged =  false;
        boolean isSufficient =  false;


        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        EntityManager entityManager = connectionInstance.getEntityManager();

        isCartChaged =  CartService.chekCart(cart,entityManager);
        // case changes has made on products
        Customer customer = (Customer) session.getAttribute("user");
        if(CartService.calculateTotalCart(cart) > customer.getCreditLimit())
        {
            isSufficient = true;
        }

        String jsonResponse = String.format("{\"cartChanged\": %b, \"creditLimitInsufficient\": %b}", isCartChaged, isSufficient);
        resp.getWriter().write(jsonResponse);

    }
}
