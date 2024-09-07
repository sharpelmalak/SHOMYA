package iti.jets.controller.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import iti.jets.dao.UserDao;
import iti.jets.model.Customer;
import iti.jets.util.ConnectionInstance;
import iti.jets.util.Factory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(value = "/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        ConnectionInstance connectionInstance=new ConnectionInstance(Factory.getEntityMangerFactory());
        UserDao userDao = new UserDao(connectionInstance.getEntityManager());
        Customer customer = (Customer) userDao.findByUsername(username);
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(customer);
        response.getWriter().write(json);
    }
}
