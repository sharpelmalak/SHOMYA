package iti.jets.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import java.io.IOException;
import java.util.Map;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get cart from session
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        // Prepare JSON response
        JsonObjectBuilder cartJsonBuilder = Json.createObjectBuilder();
        if (cart != null) {
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                cartJsonBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(cartJsonBuilder.build().toString());
    }
}
