package iti.jets.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AddProductToCartServlet")
public class AddProductToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get product details from request
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Get cart from session (or create new one if null)
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Add or update product in the cart
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);

        // Store the updated cart back in the session
        session.setAttribute("cart", cart);

        // Return success response as JSON
        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("status", "success")
                .build();

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
