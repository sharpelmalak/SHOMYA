package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.CategoryService;
import iti.jets.persistence.dao.CategoryDao;
import iti.jets.persistence.model.Category;
import iti.jets.persistence.model.Customer;
import iti.jets.business.service.RegisterationService;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


//@WebServlet(value = "/registration")
public class RegisterationServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionInstance connectionInstance = (ConnectionInstance) req.getSession().getAttribute("userConnection");
        try {
            req.setAttribute("categories", CategoryService.getCategories(connectionInstance));
            req.getRequestDispatcher("/resources/jsp/registration.jsp").forward(req, resp);
        }
        catch (Exception e) {
            resp.sendRedirect("/shomya");
        }

    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ConnectionInstance connectionInstance = (ConnectionInstance) req.getSession().getAttribute("userConnection");
        EntityManager entityManager = connectionInstance.getEntityManager();
        try{

            String name = req.getParameter("name");
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String date = req.getParameter("date");
            LocalDate Bdate = LocalDate.parse(date);
            String address = req.getParameter("address");
            float creditLimit = Float.parseFloat(req.getParameter("creditLimit"));
            String job = req.getParameter("job");
            String[] selectedCategories = req.getParameterValues("categories");
            RegisterationService registerationService=new RegisterationService();
            boolean isRegistered = registerationService.registerUser(
                    username,password,name,Date.valueOf(Bdate),job,email,creditLimit,address,selectedCategories,connectionInstance);

            if (isRegistered)
            {
                req.getRequestDispatcher("/app/login").forward(req, resp);
            }
        }
        catch(Exception e)
        {
            req.setAttribute("error", "Registeration failed");
            req.getRequestDispatcher("/resources/jsp/registration.jsp").forward(req, resp);
        }



    }

}








