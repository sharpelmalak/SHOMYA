package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.dao.ProductDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Product;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/viewProductsCategories")
public class ViewProductsCategories extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("Search product servlet get method");
        HttpSession session = request.getSession();
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
        ProductDao productDao = new ProductDao(connectionInstance.getEntityManager());
        List<Category> categories = categoryDao.findAll();
        List<Product> products = productDao.findAll();
        connectionInstance.closeEntityManager();
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.getRequestDispatcher("resources/jsp/UiProductsCategories.jsp").forward(request, response);




    }
}
