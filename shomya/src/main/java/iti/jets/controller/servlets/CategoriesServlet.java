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
        HttpSession session = req.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null)
            {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
                connectionInstance.openEntityManager();
                List<Category> categoryList = categoryDao.findAll();
                System.out.println(categoryList.size());
                req.setAttribute("categoryList", categoryList);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/categories.jsp");
                connectionInstance.closeEntityManager();
                requestDispatcher.forward(req, resp);

            }

        }
        resp.sendRedirect("/shomya/login");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
