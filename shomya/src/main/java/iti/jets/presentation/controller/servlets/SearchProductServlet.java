package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.ProductService;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SearchProductServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        String searchQuery = request.getParameter("search");

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            List<Product> products = ProductService.getProductByName(connectionInstance ,searchQuery);

            // Format the results as HTML (suggestion items)
            StringBuilder resultsHtml = new StringBuilder();
            for (Product product : products) {
                resultsHtml.append("<div class='suggestion-item'>")
                        .append(product.getName())
                        .append("</div>");
            }

            response.setContentType("text/html");
            response.getWriter().write(resultsHtml.toString());
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Search query is missing");
        }
     }

}
