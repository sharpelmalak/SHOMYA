package iti.jets.controller.servlets;

import iti.jets.dao.CustomerDao;
import iti.jets.dao.ProductDao;
import iti.jets.dao.UserDao;
import iti.jets.model.Customer;
import iti.jets.model.Order;
import iti.jets.model.Product;
import iti.jets.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(value = "/customerOrder")
public class OrdersByCustomerId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        CustomerDao customerDao = new CustomerDao(connectionInstance.getEntityManager());
        connectionInstance.openEntityManager();

        UserDao userDao=new UserDao(connectionInstance.getEntityManager());

        Integer customerId = Integer.parseInt(req.getParameter("customerId"));

        Customer customer= (Customer) userDao.findById(customerId);

        Set<Order> orders= customer.getOrders();

//        connectionInstance.closeEntityManager();
        req.setAttribute("customer", customer);
        req.setAttribute("orderList", orders);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/customer-details.jsp");
        requestDispatcher.forward(req, resp);

    }

}
