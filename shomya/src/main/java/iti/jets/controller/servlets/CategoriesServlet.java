package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.model.Category;
import iti.jets.util.ConnectionInstance;
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

@WebServlet(value = "/categories")
public class CategoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
        connectionInstance.openEntityManager();
        List<Category> categoryList = categoryDao.findAll();
        connectionInstance.closeEntityManager();
        req.setAttribute("categoryList", categoryList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/categories.jsp");
        requestDispatcher.forward(req, resp);
    }

}
