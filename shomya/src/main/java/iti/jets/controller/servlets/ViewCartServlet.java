package iti.jets.controller.servlets;

import iti.jets.model.Customer;
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

@WebServlet("/viewcart")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("user");
        System.out.println("view cart ");
        if (customer != null) {
            request.getRequestDispatcher("/shomya/resources/jsp/cart.jsp").forward(request, response);
        }
        else response.sendRedirect("/shomya");
    }
}
