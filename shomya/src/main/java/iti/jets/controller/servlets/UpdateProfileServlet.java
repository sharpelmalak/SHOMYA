package iti.jets.controller.servlets;

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

@WebServlet(value = "/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthdateStr = request.getParameter("birthdate");
        String job = request.getParameter("job");
        Float creditLimit = Float.parseFloat(request.getParameter("creditLimit"));
        String address = request.getParameter("address");

        java.sql.Date birthdate = null;
        if (birthdateStr != null && !birthdateStr.isEmpty()) {
            birthdate = java.sql.Date.valueOf(birthdateStr);
        }

        ConnectionInstance connectionInstance=new ConnectionInstance(Factory.getEntityMangerFactory());

        UserDao userDao = new UserDao(connectionInstance.getEntityManager());


        Customer customer = (Customer) userDao.findByUsername(username);

        if (customer != null) {
            customer.setName(name);
            customer.setPassword(password);
            customer.setEmail(email);
            customer.setBirthdate(birthdate);
            customer.setJob(job);
            customer.setCreditLimit(creditLimit);
            customer.setAddress(address);


            userDao.update(customer);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\", \"message\":\"User not found\"}");
        }
    }
}
