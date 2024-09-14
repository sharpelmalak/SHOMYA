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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(value = "/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // check user is logged in
        HttpSession session=request.getSession(false);

        String username = request.getParameter("username");
        String currentPassword = request.getParameter("currentPassword");  // Retrieve the current password
        String newPassword = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthdateStr = request.getParameter("date");
        String job = request.getParameter("job");
        Float creditLimit = Float.parseFloat(request.getParameter("creditLimit"));
        String address = request.getParameter("address");

        LocalDate birthdate = LocalDate.parse(birthdateStr);


        System.out.println(birthdate);

        ConnectionInstance connectionInstance = (ConnectionInstance) session.getAttribute("userConnection");
        UserDao userDao = new UserDao(connectionInstance.getEntityManager());

       Customer customer= (Customer)session.getAttribute("user");
        System.out.println(customer.getName());
        response.setContentType("application/json");

        if (customer != null) {
            // Check if the current password matches the stored hashed password
            if (!customer.getPassword().equals(userDao.hashPassword(currentPassword))) {
                response.sendRedirect("/shomya");
            }

            // Optional: Add any additional password validation rules (e.g., new password cannot contain username)
            if (newPassword.toLowerCase().contains(username.toLowerCase())) {
                response.sendRedirect("/shomya");
            }

            // Hash the new password before saving
            String hashedNewPassword = userDao.hashPassword(newPassword);

            // Update the profile if the password is valid
            customer.setName(name);
            customer.setPassword(hashedNewPassword);
            customer.setEmail(email);
            customer.setBirthdate(Date.valueOf(birthdate));
            System.out.println(customer.getBirthdate());
            customer.setJob(job);
            customer.setCreditLimit(creditLimit);
            customer.setAddress(address);

            System.out.println(customer.getName());



            userDao.update(customer);
        }

        response.sendRedirect("/shomya");


    }
}
