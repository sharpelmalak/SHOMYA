package iti.jets.controller.servlets;

import iti.jets.dao.ProductDao;
import iti.jets.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search-product")
public class SearchProductServlet extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        // Get EntityManager from ServletContext and pass it to the ProductDao
        productDao = new ProductDao((EntityManager) getServletContext().getAttribute("emf"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        // You can handle individual search/filter requests based on which parameter is provided.
        List<Product> products = null;

        if (name != null && !name.isEmpty()) {
            products = searchByName(name);
        } else if (category != null && !category.isEmpty()) {
            products = filterByCategory(category);
        } else if (minPrice != null && !minPrice.isEmpty() && maxPrice != null && !maxPrice.isEmpty()) {
            products = filterByPriceRange(Float.parseFloat(minPrice), Float.parseFloat(maxPrice));
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/results.jsp").forward(request, response);
    }

    // Search by name function
    private List<Product> searchByName(String name) {
        return productDao.searchByName(name);
    }

    // Filter by category function
    private List<Product> filterByCategory(String categoryName) {
        return productDao.filterByCategory(categoryName);
    }

    // Filter by price range function
    private List<Product> filterByPriceRange(float minPrice, float maxPrice) {
        return productDao.filterByPriceRange(minPrice, maxPrice);
    }
}
