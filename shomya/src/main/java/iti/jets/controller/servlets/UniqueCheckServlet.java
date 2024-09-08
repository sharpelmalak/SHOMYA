package iti.jets.controller.servlets;

import iti.jets.service.RegisterationService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check-unique")
public class UniqueCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        RegisterationService registrationService = new RegisterationService((EntityManagerFactory) getServletContext().getAttribute("emf"));
        boolean isUsernameUnique = registrationService.isUsernameUnique(username);
        boolean isEmailUnique = registrationService.isEmailUnique(email);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print("{\"usernameUnique\": " + isUsernameUnique + ", \"emailUnique\": " + isEmailUnique + "}");
        out.flush();
    }
}
