package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.UserService;
import iti.jets.persistence.dao.CustomerDao;
import iti.jets.persistence.dao.UserDao;
import iti.jets.persistence.model.Customer;
import iti.jets.persistence.model.Order;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;


public class OrdersByCustomerIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        try{
            Integer customerId = Integer.parseInt(req.getParameter("customerId"));
            Customer customer= (Customer) UserService.getUserById(connectionInstance,customerId);
            Set<Order> orders= customer.getOrders();
            req.setAttribute("customer", customer);
            req.setAttribute("orderList", orders);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/resources/jsp/customer-details.jsp");
            requestDispatcher.forward(req, resp);
        }
        catch (Exception e){
            resp.sendRedirect("/shomya");
        }

    }

}
