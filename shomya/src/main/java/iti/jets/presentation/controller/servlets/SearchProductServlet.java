package iti.jets.presentation.controller.servlets;

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

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);

        request.getRequestDispatcher("/resources/jsp/shop.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("search product servlet post method");

        String name=req.getParameter("name");
        float MinPrice=Float.parseFloat(req.getParameter("MinPrice"));
        float MaxPrice=Float.parseFloat(req.getParameter("MaxPrice"));

        System.out.println("name "+name);
        System.out.println("MinPrice "+MinPrice);
        System.out.println("MaxPrice "+MaxPrice);

        System.out.println("before search function");
        List<Product> filteredProducts = productDao.searchByName(name);
        System.out.println("after search function");

        req.setAttribute("filteredProducts", filteredProducts);

    }
}