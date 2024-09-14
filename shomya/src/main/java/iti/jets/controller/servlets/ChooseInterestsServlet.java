package iti.jets.controller.servlets;

import iti.jets.dao.CategoryDao;
import iti.jets.model.Category;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/chooseInterests")
public class ChooseInterestsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");

        CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());

        List<Category> categoryList = categoryDao.findAll();
        req.setAttribute("categories", categoryList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/chooseInterests.jsp");
        requestDispatcher.forward(req, resp);
    }
}

