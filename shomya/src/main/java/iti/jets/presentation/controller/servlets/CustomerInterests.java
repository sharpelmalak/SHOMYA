package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CategoryService;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class CustomerInterests extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("user");
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        req.setAttribute("categoryList", customer.getCategories());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/categories.jsp");
        requestDispatcher.forward(req, resp);

    }
}

