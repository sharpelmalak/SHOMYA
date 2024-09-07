package iti.jets.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            // to do handle cart item
            session.setAttribute("isLoged", "false");
            session.invalidate();
        }
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        sessionCookie.setMaxAge(0); // Invalidate the cookie
        sessionCookie.setPath("/"); // Make sure it applies to the entire app
        resp.addCookie(sessionCookie);
        req.getRequestDispatcher("/resources/index.jsp").forward(req, resp);
    }
}
