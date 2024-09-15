package iti.jets.presentation.controller.servlets;

import iti.jets.business.service.OrderService;
import iti.jets.persistence.dao.OrderDao;
import iti.jets.persistence.model.Order;
import iti.jets.persistence.util.ConnectionInstance;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        try{
            int id = Integer.parseInt(req.getParameter("orderId"));
            req.setAttribute("order", OrderService.getOrder(connectionInstance, id));
            req.getRequestDispatcher("/resources/jsp/order-details.jsp").forward(req, resp);
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }
}
