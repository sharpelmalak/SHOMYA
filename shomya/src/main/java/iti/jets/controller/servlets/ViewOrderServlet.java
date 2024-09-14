package iti.jets.controller.servlets;

import iti.jets.dao.OrderDao;
import iti.jets.model.Order;
import iti.jets.model.OrderItem;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/vieworder")
public class ViewOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        try{
            int id = Integer.parseInt(req.getParameter("orderId"));
            OrderDao orderDao = new OrderDao(connectionInstance.getEntityManager());
            Order order = orderDao.findById(id);
            req.setAttribute("order", order);
            req.getRequestDispatcher("/resources/jsp/order-details.jsp").forward(req, resp);
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }
}
