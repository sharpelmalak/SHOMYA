package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CategoryService;
import iti.jets.business.service.ProductService;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        List<Product> products;
        int pageSize = 6; // Number of products per page
        int pageNumber = 1; // Default to the first page
        // Get the page number from the request parameter, if provided
        String page = req.getParameter("page");
        if (page != null) {
            pageNumber = Integer.parseInt(page);
        }
        // Fetch paginated products and total pages
        products = ProductService.getPaginatedProducts(connectionInstance,pageNumber, pageSize);
        long totalPages = ProductService.getTotalPages(connectionInstance,pageSize);
        req.setAttribute("currentPage", pageNumber);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("products", products);
        req.setAttribute("categories", CategoryService.getCategories(connectionInstance));
        req.getRequestDispatcher("/resources/jsp/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the request parameters
        HttpSession session = request.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        String name = request.getParameter("name");
        String minPriceStr = request.getParameter("minprice");
        String maxPriceStr = request.getParameter("maxprice");
        String[] selectedCategories = request.getParameterValues("categories");

        Float minPrice = null;
        Float maxPrice = null;

        try {
            if (minPriceStr != null && !minPriceStr.isEmpty()) {
                minPrice = Float.parseFloat(minPriceStr);
            }
            if (maxPriceStr != null && !maxPriceStr.isEmpty()) {
                maxPrice = Float.parseFloat(maxPriceStr);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price format");
            return;
        }

        List<Product> products = ProductService.searchProducts(connectionInstance,name, minPrice, maxPrice, selectedCategories);
        request.setAttribute("categories", CategoryService.getCategories(connectionInstance));
        request.setAttribute("products", products);
        request.getRequestDispatcher("/resources/jsp/shop.jsp").forward(request, response);

    }

}
