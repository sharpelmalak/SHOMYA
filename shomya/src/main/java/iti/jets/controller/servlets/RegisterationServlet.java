package iti.jets.controller.servlets;

import iti.jets.dao.UserDao;
import iti.jets.model.Customer;
import iti.jets.model.User;
import iti.jets.service.RegisterationService;
import iti.jets.util.ConnectionInstance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

import java.time.LocalDate;


@WebServlet(value = "/registration")
public class RegisterationServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/registeration.html").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String date = req.getParameter("date");
        LocalDate Bdate = LocalDate.parse(date);
        String address = req.getParameter("address");
        float creditLimit = Float.parseFloat(req.getParameter("creditLimit"));
        String job = req.getParameter("job");
       ///
        ConnectionInstance connectionInstance = new ConnectionInstance((EntityManagerFactory) getServletContext().getAttribute("emf"));
        EntityManager entityManager = connectionInstance.getEntityManager();
        connectionInstance.openEntityManager();
        /////
        RegisterationService registerationService=new RegisterationService();
        boolean isRegistered = registerationService.registerUser(
                username,password,name,Date.valueOf(Bdate),job,email,creditLimit,address,connectionInstance);
        connectionInstance.closeEntityManager();
        if (isRegistered)
        {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/resources/registeration.html").forward(req, resp);
        }

    }

}








