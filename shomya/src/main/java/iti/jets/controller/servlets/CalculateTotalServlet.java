package iti.jets.controller.servlets;

import iti.jets.model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/calculateTotal")
public class CalculateTotalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the cart from the session
        HttpSession session = request.getSession(false);
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        double subtotal = 0.0;
        double shipping = 0.0; // Static shipping cost, can be dynamically set

        if (cart != null && !cart.isEmpty()) {
            // Calculate subtotal by summing the prices of all items in the cart
            for (CartItem item : cart) {
                subtotal += item.getProduct().getPrice() * item.getQuantity();
            }
            shipping = 10.0;
        }

        double total = subtotal + shipping;

        // Prepare JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Build JSON response with subtotal and total
        String jsonResponse = String.format("{\"subtotal\": %.2f, \"shipping\": %.2f, \"total\": %.2f}", subtotal, shipping, total);
        response.getWriter().write(jsonResponse);
    }
}

