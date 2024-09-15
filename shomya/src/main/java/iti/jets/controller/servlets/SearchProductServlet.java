package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.dao.ProductDao;
import iti.jets.model.Category;
import iti.jets.model.Product;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search-product")
public class SearchProductServlet extends HttpServlet {

    private ProductDao productDao;

    private List<Category> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Search product servlet get method");
        HttpSession session = request.getSession();
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
        ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
        categories = categoryDao.findAll();
        products = productDao.findAll();
        connectionInstance.closeEntityManager();
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.getRequestDispatcher("resources/jsp/shop.jsp").forward(request, response);

}

}
