package iti.jets.controller.servlets;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.service.AuthService;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // call service
        AuthService authService = new AuthService((EntityManagerFactory) getServletContext().getAttribute("emf"));
        User user = authService.authUser(username, password);
        if (user != null)
        {
            if(authService.getRole() == AuthService.UserRole.IS_ADMIN)
            {
                // admin redirect to admin home page
                resp.sendRedirect("resources/checkout.html");

            }
            else
            {
                //customer
                resp.sendRedirect("resources/index.html");
            }
        }
        else
        {
            // invalid credentials // error no user
            resp.sendRedirect("resources/detail.html");
        }
    }
}
