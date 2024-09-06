package iti.jets.controller.servlets;

import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.Customer;
import iti.jets.model.User;
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

        resp.sendRedirect("/shomya/resources/login.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("welcome from post");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        // call service
        ConnectionInstance connectionInstance = new ConnectionInstance((EntityManagerFactory) getServletContext().getAttribute("emf"));
        UserDao userDao  = new UserDao(connectionInstance.getEntityManager());
        User user = userDao.checkUserCredintials(username,userDao.hashPassword(password));
        System.out.println(user);
        if (user == null) {
            resp.sendRedirect("/shomya/resources/login.html");
        } else if (user instanceof Admin) {
            resp.sendRedirect("/shomya/resources/contact.html");
        }
        else if (user instanceof Customer) {
            resp.sendRedirect("/shomya/resources/index.html");
        }

    }
}
