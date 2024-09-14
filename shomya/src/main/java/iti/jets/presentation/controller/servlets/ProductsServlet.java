package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

//@WebServlet(value = "/products")
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
        // query to get first 5
        List<Product> productList = productDao.findAll();
        req.setAttribute("productList", productList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/products.jsp");
        requestDispatcher.forward(req, resp);

    }

}
