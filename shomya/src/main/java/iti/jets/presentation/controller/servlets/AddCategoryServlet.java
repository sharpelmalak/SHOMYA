package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.model.Category;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;

//@WebServlet(value = "/addCategory")
@MultipartConfig
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/shomya/app/categories");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session.getAttribute("userRole") != EnumHelper.getAdminRole())
        {
            resp.sendRedirect("/shomya");
        }

        String name = req.getParameter("categoryName");
        Part filePart = req.getPart("categoryImage");
        byte[] imageData = null;
        try (InputStream inputStream = filePart.getInputStream()) {
            imageData = inputStream.readAllBytes();
        }

        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        Category category = new Category(name,imageData);
        CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
        // Save the file to the specified directory

        categoryDao.save(category);
        resp.sendRedirect("/shomya/app/categories");

    }
}
