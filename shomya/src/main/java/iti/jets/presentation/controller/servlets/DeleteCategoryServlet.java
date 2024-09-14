package iti.jets.presentation.controller.servlets;

import iti.jets.persistence.dao.CategoryDao;
import iti.jets.business.service.helper.EnumHelper;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet("/deletecategory")
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            if(session.getAttribute("userRole")== EnumHelper.getAdminRole())
            {
                ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
                try{

                    int id = Integer.parseInt(req.getParameter("catId"));
                    CategoryDao categoryDao = new CategoryDao(connectionInstance.getEntityManager());
                    categoryDao.deleteById(id);
                    req.setAttribute("error", "Category Deleted");
                }catch (Exception e)
                {
                    req.setAttribute("error", "Error in deleting Category");
                }finally {
                    req.getRequestDispatcher("/categories").forward(req, resp);
                }

            }
            else resp.sendRedirect("/shomya");
        }
    }
}
