package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CategoryService;
import iti.jets.business.service.ProductService;
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
import java.util.ArrayList;
import java.util.List;


public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        List<Product> products;
        if(req.getParameter("search") != null) {

            products = ProductService.getProductByName(connectionInstance,req.getParameter("search"));
        }
        else  if(req.getParameter("categoryId") != null) {

            products = ProductService.getProductsFromCategory(connectionInstance, CategoryService.getCategory(connectionInstance,Integer.parseInt(req.getParameter("categoryId"))));
        }
        else
        {
            products = ProductService.getProducts(connectionInstance);
        }
        req.setAttribute("productList", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/products.jsp");
        requestDispatcher.forward(req, resp);
    }

}
