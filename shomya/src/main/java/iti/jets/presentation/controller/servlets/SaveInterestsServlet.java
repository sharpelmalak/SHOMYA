package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet("/saveInterests")
public class SaveInterestsServlet extends HttpServlet {

    @PersistenceContext

    private EntityManager entityManager;
    ConnectionInstance connectionInstance=new ConnectionInstance(entityManager.getEntityManagerFactory());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        String[] selectedCategories = request.getParameterValues("categories");
        if (selectedCategories != null) {
            for (String categoryId : selectedCategories) {
                Category category = entityManager.find(Category.class, Long.parseLong(categoryId));
                if (category != null) {
                    customer.getCategories().add(category);
                }
            }
            // Save updated customer
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();

            response.sendRedirect("resources/jsp/products.jsp");
        } else {
            response.sendRedirect("resources/jsp/chooseInterests.jsp?error=Please select at least one category.");
        }
    }
}

