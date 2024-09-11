package iti.jets.controller.servlets;

import iti.jets.dao.CustomerDao;
import iti.jets.dao.ProductDao;
import iti.jets.dao.UserDao;
import iti.jets.model.Admin;
import iti.jets.model.Customer;
import iti.jets.model.Product;
import iti.jets.model.User;
import iti.jets.util.ConnectionInstance;
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

@WebServlet(value = "/customers")
public class ViewCustomersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        UserDao userDao = new UserDao(connectionInstance.getEntityManager());
        connectionInstance.openEntityManager();

        List<User> customerList = userDao.findAll();

        connectionInstance.closeEntityManager();
        Iterator<User> iterator = customerList.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user instanceof Admin) {
                iterator.remove();  // Safely removes the Admin user
            }
        }

        req.setAttribute("customertList", customerList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/customers.jsp");
        requestDispatcher.forward(req, resp);

    }

}
