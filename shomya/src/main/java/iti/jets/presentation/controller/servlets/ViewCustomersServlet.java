package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.UserService;
import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.Admin;
import iti.jets.persistence.model.User;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class ViewCustomersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        req.setAttribute("customertList", UserService.getCustomers(connectionInstance));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/customers.jsp");
        requestDispatcher.forward(req, resp);
    }

}
